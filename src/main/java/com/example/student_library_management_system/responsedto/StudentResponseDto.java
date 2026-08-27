package com.example.student_library_management_system.responsedto;

import com.example.student_library_management_system.model.Student;
import lombok.Data;

@Data
public class StudentResponseDto {

    // ResponseDto - used to send the response back to user in the customized format

    private String message;
    private Student savedStudent;
}
