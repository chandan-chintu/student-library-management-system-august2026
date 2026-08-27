package com.example.student_library_management_system.requestdto;

import lombok.Data;

@Data
public class BookRequestDto {

    private String title;
    private String publisherName;
    private String publishedDate;
    private int pages;
    private boolean availability;
    private String category;
    private String rackNo;
}
