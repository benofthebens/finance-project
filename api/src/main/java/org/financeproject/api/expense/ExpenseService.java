package org.financeproject.api.expense;

import org.financeproject.utils.Status;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExpenseService {
    @GetMapping(
            produces = "application/json",
            path = "/api/expense"
    )
    Flux<Expense> getExpenses(
            @RequestParam String userId,
            @RequestParam(required = false) Status status
    );

    @PostMapping(
            consumes = "application/json",
            path = "/api/expense"
    )
    Mono<Void> createExpense(
            @RequestBody Expense expense
    );
}
