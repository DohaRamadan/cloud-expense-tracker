package com.expenseservice.repository;

import java.util.List;

import com.expenseservice.model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExpenseRepository extends MongoRepository<Expense, String> {

    List<Expense> findByUserUsername(String username);
}
