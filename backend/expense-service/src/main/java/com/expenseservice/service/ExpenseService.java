package com.expenseservice.service;

import java.util.List;
import java.util.Objects;

import com.expenseservice.dto.ExpenseRequest;
import com.expenseservice.dto.ExpenseResponse;
import com.expenseservice.dto.MessageResponse;
import com.expenseservice.model.Expense;
import com.expenseservice.repository.ExpenseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }

    public void addExpense(ExpenseRequest expenseRequest, String username){
        Expense expense = Expense.builder()
                .name(expenseRequest.getName())
                .amount(expenseRequest.getAmount())
                .category(expenseRequest.getCategory())
                .date(expenseRequest.getDate())
                .userUsername(username)
                .build();
        expenseRepository.save(expense);
        log.info("Expense {} is saved", expense.getId());
    }

    public ResponseEntity<MessageResponse> deleteExpense(String id, String username){
        Expense savedExpense = expenseRepository.findById(id)
                                                .orElseThrow(() -> new RuntimeException(
                                                        String.format("Cannot find expense by ID %s", id)
                                                ));
        if(!Objects.equals(savedExpense.getUserUsername(), username)){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("You do not have access to this expense"));
        }
        expenseRepository.deleteById(id);
        return ResponseEntity
                .ok()
                .body(new MessageResponse("Expense has been deleted successfully"));
    }


    private ExpenseResponse mapToExpenseResponse(Expense expense) {
        return ExpenseResponse.builder()
                .name(expense.getName())
                .Id(expense.getId())
                .amount(expense.getAmount())
                .category(expense.getCategory())
                .date(expense.getDate())
                .username(expense.getUserUsername())
                .build();
    }

    public ResponseEntity<MessageResponse> updateExpense(Expense expense, String username){
        Expense savedExpense = expenseRepository.findById(expense.getId())
                                                .orElseThrow(() -> new RuntimeException(
                                                        String.format("Cannot find expense by ID %s", expense.getId())
                                                ));
        if(!Objects.equals(savedExpense.getUserUsername(), username)){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("You do not have access to this expense"));
        }
        savedExpense.setAmount(expense.getAmount());
        savedExpense.setCategory(expense.getCategory());
        savedExpense.setName(expense.getName());

        expenseRepository.save(savedExpense);
         return ResponseEntity
                 .ok()
                .body(new MessageResponse("Expense has been updated successfully"));
    }

    public List<ExpenseResponse> getByUsername(String username){
        List<Expense> expenses = expenseRepository.findByUserUsername(username);
        return expenses.stream().map(this::mapToExpenseResponse).toList();
    }
}
