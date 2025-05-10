package org.financeproject.service.expenseservice;

import org.financeproject.api.expense.Expense;
import org.financeproject.api.expense.ExpenseService;
import org.financeproject.service.expenseservice.persitence.ExpenseEntity;
import org.financeproject.service.expenseservice.persitence.ExpenseRepository;
import org.financeproject.service.expenseservice.services.ExpenseMapper;
import org.financeproject.utils.Status;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, ExpenseMapper expenseMapper) {
        this.expenseRepository = expenseRepository;
        this.expenseMapper = expenseMapper;
    }

    @Override
    public Flux<Expense> getExpenses(Status status) {
        if (status == null) {
            return Flux.fromStream(
                    expenseRepository.findAll().stream().map(expenseMapper::entityToApi)
            );
        }
        return Flux.fromStream(
                expenseRepository.getExpenseEntitiesByStatus(status).stream()
                        .map(expenseMapper::entityToApi)
        );
    }

    @Override
    public Mono<Void> createExpense(Expense expense) {
        ExpenseEntity expenseEntity = expenseMapper.apiToEntity(expense);
        expenseEntity.setExpenseId(UUID.randomUUID().toString());
        return Mono.fromRunnable(() -> expenseRepository.save(expenseEntity));
    }
}
