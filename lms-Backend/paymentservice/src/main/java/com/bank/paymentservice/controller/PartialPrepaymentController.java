package com.bank.paymentservice.controller;

import com.bank.paymentservice.model.PartialPrepayment;
import com.bank.paymentservice.service.PartialPrepaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments/prepayment")
public class PartialPrepaymentController {

    private final PartialPrepaymentService partialPrepaymentService;

    public PartialPrepaymentController(PartialPrepaymentService partialPrepaymentService) {
        this.partialPrepaymentService = partialPrepaymentService;
    }

    // ================= MAKE PREPAYMENT =================
    @PostMapping("/{loanId}")
    public PartialPrepayment makePrepayment(@PathVariable Long loanId,
                                            @RequestParam Double amount,
                                            @RequestParam Double remainingPrincipal) {

        return partialPrepaymentService.makePrepayment(
                loanId,
                amount,
                remainingPrincipal
        );
    }

    // ================= PREPAYMENT HISTORY =================
    @GetMapping("/{loanId}")
    public List<PartialPrepayment> getHistory(@PathVariable Long loanId) {
        return partialPrepaymentService.getPrepaymentHistory(loanId);
    }
}
