package com.expenseservice.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("expense")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Expense {
    @Id
    private String id;
    private String name;
    private com.expenseservice.model.ExpenseCategory category;
    private BigDecimal amount;
    private Date date;
    private String userUsername;
}
