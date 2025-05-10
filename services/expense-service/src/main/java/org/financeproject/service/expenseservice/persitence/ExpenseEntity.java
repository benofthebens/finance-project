package org.financeproject.service.expenseservice.persitence;

import jakarta.persistence.*;
import org.financeproject.utils.Status;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name="EXPENSE")
public class ExpenseEntity {
    @Id
    @Column(name="EXPENSE_ID")
    private String expenseId;
    @Column(name="DESCRIPTION")
    private String description;
    @Column(name="STATUS")
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name="AMOUNT")
    private double amount;
    @Column(name="EXPENSE_DATE")
    private LocalDate expenseDate;
    public ExpenseEntity() {

    }
    public ExpenseEntity(String expenseId, String description, Status status, double amount, LocalDate expenseDate) {
        this.expenseId = expenseId;
        this.description = description;
        this.status = status;
        this.amount = amount;
        this.expenseDate = expenseDate;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }
}
