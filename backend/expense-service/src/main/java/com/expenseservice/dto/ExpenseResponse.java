package com.expenseservice.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.expenseservice.model.ExpenseCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ExpenseResponse {
    private String Id;
    private String name;
    private ExpenseCategory category;
    private BigDecimal amount;
    private Date date;
    private String username;
}
