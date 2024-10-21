package com.abraham.creditservice.controller;

import com.abraham.creditservice.models.*;
import com.abraham.creditservice.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/credits")
@RequiredArgsConstructor
public class CreditController {
    private final CreditService service;

    @PostMapping
    public ResponseEntity<CreditResponse> createNewCredit(
            @RequestBody CreateNewCreditRequest request
    ) {
        final var result = service.createNewCredit(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/list")
    public ResponseEntity<List<CreditResponse>> createNewCredits(
            @RequestBody List<CreateNewCreditRequest> requests
    ) {
        final var result = service.createNewCredits(requests);
        return ResponseEntity.ok(result);
    }

    @PatchMapping
    public ResponseEntity<CreditResponse> payCredit(
            @RequestBody PayRequest request
    ) {
        final var result = service.payCredit(request);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get-metrics")
    public ResponseEntity<List<CreditMetrics>> getAllDeniedAndAcceptedCredits() {
        final var metrics = service.getMetrics();
        return ResponseEntity.ok(metrics);
    }

    @GetMapping("/get-statistics")
    public ResponseEntity<StatisticsModel> getStatistics() {
        final var statistics = service.getStatistics();
        return ResponseEntity.ok(statistics);
    }
}
