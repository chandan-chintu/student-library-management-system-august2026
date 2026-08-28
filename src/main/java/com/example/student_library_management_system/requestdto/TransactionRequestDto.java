package com.example.student_library_management_system.requestdto;

import com.example.student_library_management_system.enums.TransactionType;

import lombok.Data;

@Data
public class TransactionRequestDto {
    private String dueDate; // we can remove it as we are calculating automatically in transaction service
    private TransactionType transactionType;

    private int cardId;
    private int bookId;
}
