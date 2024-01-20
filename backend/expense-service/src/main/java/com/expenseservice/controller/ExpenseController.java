package com.expenseservice.controller;

import java.util.List;

import com.expenseservice.dto.ExpenseRequest;
import com.expenseservice.dto.ExpenseResponse;
import com.expenseservice.dto.MessageResponse;
import com.expenseservice.model.Expense;
import com.expenseservice.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @PostMapping
    public void addExpense(@RequestBody ExpenseRequest expenseRequest, @RequestHeader("X-Auth-Username") String username){
        expenseService.addExpense(expenseRequest, username);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteExpense(@PathVariable String id, @RequestHeader("X-Auth-Username") String username){
        return expenseService.deleteExpense(id, username);
    }

    @GetMapping
    public List<ExpenseResponse> getAllExpenses(@RequestHeader("X-Auth-Username") String username){
        return expenseService.getByUsername(username);
    }

    @PutMapping
    public ResponseEntity<MessageResponse> updateExpense(@RequestBody Expense expense, @RequestHeader("X-Auth-Username") String username ){
        return expenseService.updateExpense(expense, username);
    }
}
