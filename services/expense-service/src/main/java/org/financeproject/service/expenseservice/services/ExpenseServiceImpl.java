package org.financeproject.service.expenseservice.services;

import org.financeproject.api.expense.Expense;
import org.financeproject.api.expense.ExpenseService;
import org.financeproject.service.expenseservice.events.KafkaEventProducer;
import org.financeproject.service.expenseservice.persitence.ExpenseEntity;
import org.financeproject.service.expenseservice.persitence.ExpenseRepository;
import org.financeproject.utils.Status;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    private final KafkaEventProducer kafkaEventProducer;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, ExpenseMapper expenseMapper, KafkaEventProducer kafkaEventProducer) {
        this.expenseRepository = expenseRepository;
        this.expenseMapper = expenseMapper;
        this.kafkaEventProducer = kafkaEventProducer;
    }

    @Override
    public Flux<Expense> getExpenses(String userId, Status status) {
        kafkaEventProducer.sendMessage("my-topic", userId);
        if (status == null) {
            return Flux.fromStream(
                    expenseRepository.getAllByUserId(userId).stream().map(expenseMapper::entityToApi)
            );
        }
        return Flux.fromStream(
                expenseRepository.getExpenseEntitiesByStatusAndUserId(status, userId).stream()
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
