package org.financeproject.service.expenseservice.services;

import org.financeproject.api.expense.Expense;
import org.financeproject.service.expenseservice.persitence.ExpenseEntity;
import org.financeproject.utils.mapper.Mapper;

public class ExpenseMapper implements Mapper<Expense, ExpenseEntity> {
    @Override
    public Expense entityToApi(ExpenseEntity entity) {
        return new Expense(
                entity.getExpenseId(),
                entity.getUserId(),
                entity.getAmount(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getExpenseDate()
        );
    }

    @Override
    public ExpenseEntity apiToEntity(Expense api) {
        return new ExpenseEntity(
                api.getExpenseId(),
                api.getUserId(),
                api.getDescription(),
                api.getStatus(),
                api.getAmount(),
                api.getExpenseDate()
        );
    }
}
