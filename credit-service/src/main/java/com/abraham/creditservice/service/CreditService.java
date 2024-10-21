package com.abraham.creditservice.service;

import com.abraham.creditservice.clients.CustomerClient;
import com.abraham.creditservice.models.CreditMetrics;
import com.abraham.creditservice.models.*;
import com.abraham.creditservice.repository.CreditRepository;
import com.abraham.creditservice.repository.EntityManagerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditService {
    private final CreditRepository repository;
    private final CustomerClient customerClient;
    private final EntityManagerRepository entityManagerRepository;

    @Transactional
    public CreditResponse createNewCredit(CreateNewCreditRequest request) {
        CreditStatus status = CreditStatus.DENIED;
        if (hasEnoughCreditLimit(request.getCustomerId(), request.getAmount())) {
            status = CreditStatus.ACTIVE;
        }

        final var created = repository.save(
                CreditMapper.fromNewCreditRequest(request, status)
        );
        return CreditMapper.fromCredit(created);
    }

    private Boolean hasEnoughCreditLimit(int customerId, double creditRequested) {
        final var creditUsed = repository.creditUsed(customerId).orElse(0d);
        return customerClient.hasEnoughCredit(creditUsed + creditRequested, customerId);
    }

    public CreditResponse payCredit(PayRequest request) {
        final var credit = repository.findById(request.id())
                .orElseThrow(() -> new RuntimeException("Credit not found"));
        if (!credit.getStatus().equals(CreditStatus.ACTIVE)) {
            throw new RuntimeException("Operation not valid");
        }
        credit.setStatus(CreditStatus.CLOSED);
        customerClient.increaseMaxCredit(credit.getCustomerId(), (float) credit.getAmount() * 0.05f);
        return CreditMapper.fromCredit(repository.save(credit));

    }

    @Transactional
    public List<CreditResponse> createNewCredits(List<CreateNewCreditRequest> requests) {
        List<CreditResponse> responses = new ArrayList<>();
        for (CreateNewCreditRequest request : requests) {
            responses.add(createNewCredit(request));
        }
        return responses;
    }

    public List<CreditMetrics> getMetrics() {
        final var metrics = repository.getMetrics();
        return metrics.stream()
                .map(tuple -> CreditMetrics.builder()
                        .count(tuple.get(0, Long.class))
                        .status(tuple.get(1, CreditStatus.class))
                        .build())
                .collect(Collectors.toList());
    }

    public StatisticsModel getStatistics() {
        return entityManagerRepository.getStatistics();
    }
}
