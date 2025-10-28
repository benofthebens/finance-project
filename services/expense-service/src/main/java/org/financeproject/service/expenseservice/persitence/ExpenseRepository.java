package org.financeproject.service.expenseservice.persitence;

import org.financeproject.utils.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, String> {
    List<ExpenseEntity> getAllByUserId(String userId);
    List<ExpenseEntity> getExpenseEntitiesByStatusAndUserId(Status status, String userId);
}
