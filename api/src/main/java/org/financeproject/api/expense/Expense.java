package org.financeproject.api.expense;


import org.financeproject.utils.Status;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

public class Expense {
    private String expenseId;
    private String userId;
    private double amount;
    private String description;
    private Status status;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate expenseDate;

    public Expense() {
        this("", "", 0,"", Status.PENDING, LocalDate.now());
    }
    public Expense(String expenseId,String userId, double amount, String description, Status status, LocalDate expenseDate) {
        this.expenseId = expenseId;
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.expenseDate = expenseDate;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
