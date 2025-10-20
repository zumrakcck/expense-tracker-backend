package com.example.expense_tracker.service;

import com.example.expense_tracker.model.Expense;
import com.example.expense_tracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;
import java.util.List;


//CRUD İŞLEMLERİ 

@Service
public class ExpenseService {
    private final ExpenseRepository repo;

    public ExpenseService(ExpenseRepository repo) {
        this.repo = repo;
    }

    public Expense addExpense(Expense expense) {
        return repo.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return repo.findAll();
    }

    public Expense getExpense(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
    }

    public Expense updateExpense(Long id, Expense expense) {
        Expense existing = getExpense(id);
        existing.setDescription(expense.getDescription());
        existing.setCategory(expense.getCategory());
        existing.setAmount(expense.getAmount());
        existing.setDate(expense.getDate());
        return repo.save(existing);
    }

    public void deleteExpense(Long id) {
        repo.deleteById(id);
    }
}
