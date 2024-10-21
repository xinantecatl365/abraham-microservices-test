package com.abraham.branch.branch;

import com.abraham.branch.models.CustomerRequest;
import com.abraham.branch.models.GetCountRequest;
import com.abraham.branch.models.NewCreditRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;

    public Integer createNewCustomer(CustomerRequest request) {
        return 1;
    }


    public Integer requestNewCredit(NewCreditRequest request) {
        return 1;
    }


    public Boolean getApprovedAndDeniedCredits(
            GetCountRequest request
    ) {
        return false;
    }


}
