package com.example.student_library_management_system.service;

import com.example.student_library_management_system.model.Card;
import com.example.student_library_management_system.model.Student;
import com.example.student_library_management_system.repository.StudentRepository;
import com.example.student_library_management_system.requestdto.StudentRequestDto;
import com.example.student_library_management_system.responsedto.StudentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public StudentResponseDto saveStudent(StudentRequestDto studentRequestDto){
        // convert the request dto into model class
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setEmail(studentRequestDto.getEmail());
        student.setSem(studentRequestDto.getSem());
        student.setDept(studentRequestDto.getDept());
        student.setMobile(studentRequestDto.getMobile());
        student.setAddress(studentRequestDto.getAddress());
        student.setDob(studentRequestDto.getDob());
        student.setGender(studentRequestDto.getGender());

        // whenever student adds, card also gets added as part of cascading
        Card card = new Card();
        card.setCardStatus("ACTIVE");
        card.setExpiryDate(LocalDate.now().plusYears(3).toString());
        card.setStudent(student);

        student.setCard(card);

        student = studentRepository.save(student);

        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setMessage("Student saved successfully!");
        studentResponseDto.setSavedStudent(student);

        return studentResponseDto;
    }

    public Student findStudentById(int id){
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(studentOptional.isPresent()){
            return studentOptional.get();
        } else {
            throw new RuntimeException("Student with id : "+id+" not found");
        }
    }

    public List<Student> findAllStudents(){
        List<Student> studentList = studentRepository.findAll();
        return studentList;
    }

    /*
    Pagination - fetching or getting the records or data in the form of pages
    pagenumber - the number of page we want to see(0,1,2,3,4,5...)
    pagesize - total number of records in each page(fixed for each page)

    total number of record - 28, page size - 5
    0th page - 1-5
    1st page - 6-10
    2nd page - 11-15
    3rd page - 16-20
    4th page - 21-25
    5th page - 26-28

    total numbers of records-11, page size-3
    0th page - 1-3
    1st page - 4-6
    2nd page - 7-9
    3rd page - 10-11

     */

    public Page<Student> findStudentByPage(int pageNo, int pageSize){
        Page<Student> studentPage = studentRepository.findAll(PageRequest.of(pageNo, pageSize));
        return studentPage;
    }

    // sorting - arranging the records based on ascending or descending order of some fields
    public List<Student> sortStudentByField(String sortBy, String orderBy){
        List<Student> studentList = null;
        if(orderBy.equalsIgnoreCase("ascending")){
            studentList = studentRepository.findAll(Sort.by(sortBy).ascending());
        } else if(orderBy.equalsIgnoreCase("descending")){
            studentList = studentRepository.findAll(Sort.by(sortBy).descending());
        }
        return studentList;
    }

    //pagination and sorting
    public Page<Student> findStudentByPageAndSort(int pageNo, int pageSize, String sortBy, String orderBy){
        Page<Student> studentPage = null;
        if(orderBy.equalsIgnoreCase("ascending")){
            studentPage = studentRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending()));
        } else if(orderBy.equalsIgnoreCase("descending")){
            studentPage = studentRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending()));
        }
        return studentPage;
    }


    public String deleteStudentById(int id){
        Student student = findStudentById(id);
        if(student!=null){
            studentRepository.deleteById(id);
            return "Student with id : "+id+" deleted successfully!";
        } else {
            throw new RuntimeException("Student with id : "+id+" not found");
        }
    }

    public String updateStudent(int id, StudentRequestDto studentRequestDto){
        Student existingStudent = findStudentById(id);
        if(existingStudent!=null){
            existingStudent.setName(studentRequestDto.getName());
            existingStudent.setDept(studentRequestDto.getDept());
            existingStudent.setSem(studentRequestDto.getSem());
            existingStudent.setMobile(studentRequestDto.getMobile());
            existingStudent.setAddress(studentRequestDto.getAddress());
            existingStudent.setGender(studentRequestDto.getGender());
            existingStudent.setEmail(studentRequestDto.getEmail());
            existingStudent.setDob(studentRequestDto.getDob());

            studentRepository.save(existingStudent);

            return "Student with id : "+id+" updated successfully!";
        } else{
            throw new RuntimeException("Student with id : "+id+" not found");
        }
    }

    public Student findStudentByEmail(String email){
        Student student = studentRepository.getStudentByEmail(email);
        return student;
    }

    public List<Student> findStudentByDept(String dept){
        List<Student> studentList = studentRepository.getStudentByDept(dept);
        return studentList;
    }
}
