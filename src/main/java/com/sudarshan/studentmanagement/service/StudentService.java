package com.sudarshan.studentmanagement.service;

import java.util.List;

import com.sudarshan.studentmanagement.dto.PagedResponseDTO;
import com.sudarshan.studentmanagement.dto.StudentRequestDTO;
import com.sudarshan.studentmanagement.dto.StudentResponseDTO;
import com.sudarshan.studentmanagement.model.Student;

public interface StudentService {

	PagedResponseDTO<StudentResponseDTO> getAllStudents(
	        int page, int size, String sort);



    Student getStudentById(Long id);

    StudentResponseDTO createStudent(StudentRequestDTO dto);

    StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto);


    void deleteStudent(Long id);
    
    PagedResponseDTO<StudentResponseDTO> searchByName(
            String name, int page, int size, String sort);

    PagedResponseDTO<StudentResponseDTO> searchByEmail(
            String email, int page, int size, String sort);
}
