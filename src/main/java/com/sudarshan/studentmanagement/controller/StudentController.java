//package com.sudarshan.studentmanagement.controller;
//
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import com.sudarshan.studentmanagement.model.Student;
//import com.sudarshan.studentmanagement.repository.StudentRepository;
//
//@RestController
//@RequestMapping("/api/students")
//public class StudentController {
//
//    @Autowired
//    private StudentRepository studentRepository;
//
//    // GET all students
//    @GetMapping
//    public List<Student> getAllStudents() {
//        return studentRepository.findAll();
//    }
//
//    // GET student by id
//    @GetMapping("/{id}")
//    public Student getStudentById(@PathVariable Long id) {
//        return studentRepository.findById(id).orElse(null);
//    }
//
//    // POST - add new student
//    @PostMapping
//    public Student createStudent(@RequestBody Student student) {
//        return studentRepository.save(student);
//    }
//
//    // PUT - update student
//    @PutMapping("/{id}")
//    public Student updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
//        Student student = studentRepository.findById(id).orElse(null);
//        if (student != null) {
//            student.setName(studentDetails.getName());
//            student.setEmail(studentDetails.getEmail());
//            student.setAge(studentDetails.getAge());
//            return studentRepository.save(student);
//        }
//        return null;
//    }
//
//    // DELETE student
//    @DeleteMapping("/{id}")
//    public String deleteStudent(@PathVariable Long id) {
//        studentRepository.deleteById(id);
//        return "Student deleted with id: " + id;
//    }
//}

package com.sudarshan.studentmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sudarshan.studentmanagement.dto.PagedResponseDTO;
import com.sudarshan.studentmanagement.dto.StudentRequestDTO;
import com.sudarshan.studentmanagement.dto.StudentResponseDTO;
import com.sudarshan.studentmanagement.model.Student;
import com.sudarshan.studentmanagement.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public PagedResponseDTO<StudentResponseDTO> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sort) {

        return studentService.getAllStudents(page, size, sort);
    }


    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public StudentResponseDTO createStudent( @Valid @RequestBody StudentRequestDTO dto)
 {
        return studentService.createStudent(dto);
    }
   
    @PutMapping("/{id}")
    public StudentResponseDTO updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDTO dto) {

        return studentService.updateStudent(id, dto);
    }


    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "Student deleted with id: " + id;
    }
}

