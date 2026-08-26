package com.example.student_library_management_system.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="card")
@Data
public class Card {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="card_status",nullable = false)
    private String cardStatus;

    @Column(name="expiry_date",nullable = false)
    private String expiryDate;

    @Column(name="created_date",nullable = false)
    @CreationTimestamp //when a new card is created/issued it will automatically add date and time
    private Date createdDate;

    @Column(name="updated_date",nullable = false)
    @UpdateTimestamp // when a card is updated it will automatically update date and time
    private Date updatedDate;

    @JoinColumn // it joins the primary key student id from student table as a foreign key in card table
    @OneToOne // one card will be assigned to one student
    private Student student;

    @OneToMany(mappedBy = "card")
    private List<Transaction> transactionList;
}
