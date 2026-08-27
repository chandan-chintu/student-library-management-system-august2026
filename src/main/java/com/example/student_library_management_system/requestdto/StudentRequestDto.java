package com.example.student_library_management_system.requestdto;

import lombok.Data;

@Data
public class StudentRequestDto {

    // dto(data transfer object) - it is used to take the inputs into APIs and send response back to front end
    // request dto(data transfer object) - it is used to take the inputs into APIs.

    //we can  use model class itself to take input even that will work as we have seen in hospital management db project
    // but we should follow standard practice of maintaining different layers in spring boot project
    // this helps us to clearly differentiate between model class and request dto class


    private String name;
    private String email;
    private String dept;
    private String mobile;
    private String sem;
    private String gender;
    private String address;
    private String dob;
}
