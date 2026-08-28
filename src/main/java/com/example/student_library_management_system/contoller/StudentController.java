package com.example.student_library_management_system.contoller;

import com.example.student_library_management_system.model.Student;
import com.example.student_library_management_system.requestdto.StudentRequestDto;
import com.example.student_library_management_system.responsedto.StudentResponseDto;
import com.example.student_library_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/apis")
public class StudentController {

    /*
User request (either postman or UI) -> dispatcher servlet ->
corresponding API (controller class based on endpoint or url) ->
service (business logic) -> repository (to perform various database operations)
 */

    @Autowired
    StudentService studentService;

    //standard way for returning response - ResponseEntity (it contains http status code and response body)

    @PostMapping("/save")
    public ResponseEntity<?> saveStudent(@RequestBody StudentRequestDto studentRequestDto){
        try {
            StudentResponseDto studentResponseDto = studentService.saveStudent(studentRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(studentResponseDto);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occurred : "+e.getMessage());
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findStudentById(@PathVariable int id){
        try {
            Student student = studentService.findStudentById(id);
            return ResponseEntity.status(HttpStatus.OK).body(student);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occurred : "+e.getMessage());
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAllStudents(){
        try {
            List<Student> studentList = studentService.findAllStudents();
            return ResponseEntity.status(HttpStatus.OK).body(studentList);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occurred : "+e.getMessage());
        }
    }

    @GetMapping("/findByPage")
    public ResponseEntity<?> findAllStudentsByPage(@RequestParam int pageNo, @RequestParam int pageSize){
        try {
            Page<Student> studentPage = studentService.findStudentByPage(pageNo, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(studentPage);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occurred : "+e.getMessage());
        }
    }

    @GetMapping("/sort")
    public ResponseEntity<?> sortStudentByField(@RequestParam String sortBy, @RequestParam String orderBy){
        try {
            List<Student> studentList = studentService.sortStudentByField(sortBy,orderBy);
            return ResponseEntity.status(HttpStatus.OK).body(studentList);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occurred : "+e.getMessage());
        }
    }

    @GetMapping("/findByPageAndSort")
    public ResponseEntity<?> findAllStudentsByPageAndSort(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String sortBy, @RequestParam String orderBy){
        try {
            Page<Student> studentPage = studentService.findStudentByPageAndSort(pageNo, pageSize, sortBy,orderBy);
            return ResponseEntity.status(HttpStatus.OK).body(studentPage);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occurred : "+e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable int id){
        try {
            String response = studentService.deleteStudentById(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occurred : "+e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable int id, @RequestBody StudentRequestDto studentRequestDto){
        try {
            String response  = studentService.updateStudent(id, studentRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occurred : "+e.getMessage());
        }
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<?> findStudentByEmail(@RequestParam String email){
        try {
            Student student = studentService.findStudentByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(student);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occurred : "+e.getMessage());
        }
    }

    @GetMapping("/findByDept")
    public ResponseEntity<?> findStudentsByDept(@RequestParam String dept){
        try {
            List<Student> studentList = studentService.findStudentByDept(dept);
            return ResponseEntity.status(HttpStatus.OK).body(studentList);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occurred : "+e.getMessage());
        }
    }
}
