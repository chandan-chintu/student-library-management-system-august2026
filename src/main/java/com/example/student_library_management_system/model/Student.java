package com.example.student_library_management_system.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="student")
@Data
public class Student {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="student_name", nullable = false)
    private String name;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="dept", nullable = false)
    private String dept;

    @Column(name="mobile", nullable = false, unique = true)
    private String mobile;

    @Column(name="sem", nullable = false)
    private String sem;

    @Column(name="gender", nullable = false)
    private String gender;

    @Column(name="address", nullable = false)
    private String address;

    @Column(name="dob", nullable = false)
    private String dob;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)// one student will have one card
    private Card card;
}
