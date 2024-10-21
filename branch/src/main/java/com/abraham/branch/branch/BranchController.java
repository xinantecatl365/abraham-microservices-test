package com.abraham.branch.branch;

import com.abraham.branch.models.CustomerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/branches")
public class BranchController {

    private final BranchService branchService;

    @PostMapping
    public ResponseEntity<?> createNewCustomer(
            @RequestBody CustomerRequest customerRequest
    ) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/credit")
    public ResponseEntity<?> requestNewCredit(
//            @RequestBody CustomerRequest customerRequest
    ) {
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getApprovedAndDeniedCredits() {
        return ResponseEntity.ok().build();
    }


}
