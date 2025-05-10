package org.financeproject.api.expense;

import org.financeproject.utils.Status;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExpenseService {
    @GetMapping(
            produces = "application/json",
            path = "/get"
    )
    Flux<Expense> getExpenses(@RequestParam(required = false) Status status);

    @PostMapping(
            consumes = "application/json",
            path = "/create"
    )
    Mono<Void> createExpense(@RequestBody Expense expense);
}
