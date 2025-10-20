package com.example.expense_tracker.controller;

import com.example.expense_tracker.model.Expense;
import com.example.expense_tracker.service.ExpenseService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin("*")

//REST endpoint’leri oluşturdum.

public class ExpenseController {

    private final ExpenseService service;
    private final MessageSource messageSource;

    public ExpenseController(ExpenseService service, MessageSource messageSource) {
        this.service = service;
        this.messageSource = messageSource;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Expense expense) {
        Expense savedExpense = service.addExpense(expense);
        String successMessage = messageSource.getMessage("expense.add.success", null, LocaleContextHolder.getLocale());
        return ResponseEntity.ok().body(Map.of(
            "message", successMessage,
            "expense", savedExpense
        ));
    }

    @GetMapping
    public List<Expense> getAll() {
        return service.getAllExpenses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getExpense(id));
        } catch (RuntimeException e) {
            String errorMessage = messageSource.getMessage("expense.not.found", null, LocaleContextHolder.getLocale());
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Expense expense) {
        try {
            Expense updatedExpense = service.updateExpense(id, expense);
            String successMessage = messageSource.getMessage("expense.update.success", null, LocaleContextHolder.getLocale());
            return ResponseEntity.ok().body(Map.of(
                "message", successMessage,
                "expense", updatedExpense
            ));
        } catch (RuntimeException e) {
            String errorMessage = messageSource.getMessage("expense.not.found", null, LocaleContextHolder.getLocale());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.deleteExpense(id);
            String successMessage = messageSource.getMessage("expense.delete.success", null, LocaleContextHolder.getLocale());
            return ResponseEntity.ok().body(Map.of("message", successMessage));
        } catch (RuntimeException e) {
            String errorMessage = messageSource.getMessage("expense.not.found", null, LocaleContextHolder.getLocale());
            return ResponseEntity.notFound().build();
        }
    }
}
