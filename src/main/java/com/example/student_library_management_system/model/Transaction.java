package com.example.student_library_management_system.model;

import com.example.student_library_management_system.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "transaction_details")
@Data
public class Transaction {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="transaction_date",nullable = false)
    @CreationTimestamp
    private Date transactionDate;

    @Column(name="due_date")
    private String dueDate;

    @Column(name="transaction_type",nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

    @JoinColumn
    @ManyToOne
    private Card card;

    @JoinColumn
    @ManyToOne
    private Book book;

}
