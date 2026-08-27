package com.example.student_library_management_system.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="book")
@Data
public class Book {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="publisher_name")
    private String publisherName;

    @Column(name="published_date")
    private String publishedDate;

    @Column(name="pages",nullable = false)
    private int pages;

    @Column(name="availability",nullable = false)
    private boolean availability;

    @Column(name="category",nullable = false)
    private String category;

    @Column(name="rackNo",nullable = false)
    private String rackNo;

    @JsonManagedReference
    @OneToMany(mappedBy = "book")
    private List<Transaction> transactionList;
}
