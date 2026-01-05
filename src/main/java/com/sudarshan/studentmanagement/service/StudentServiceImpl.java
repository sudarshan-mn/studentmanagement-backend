package com.sudarshan.studentmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sudarshan.studentmanagement.dto.PagedResponseDTO;
import com.sudarshan.studentmanagement.dto.StudentRequestDTO;
import com.sudarshan.studentmanagement.dto.StudentResponseDTO;
import com.sudarshan.studentmanagement.exception.StudentNotFoundException;
import com.sudarshan.studentmanagement.model.Student;
import com.sudarshan.studentmanagement.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public PagedResponseDTO<StudentResponseDTO> getAllStudents(
            int page, int size, String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Student> studentPage = studentRepository.findAll(pageable);

        List<StudentResponseDTO> dtoList = new ArrayList<>();

        for (Student s : studentPage.getContent()) {
            dtoList.add(new StudentResponseDTO(
                    s.getId(),
                    s.getName(),
                    s.getEmail(),
                    s.getAge()
            ));
        }

        return new PagedResponseDTO<>(
                dtoList,
                studentPage.getNumber(),
                studentPage.getTotalPages(),
                studentPage.getTotalElements(),
                studentPage.getSize(),
                studentPage.isLast()
        );
    }

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO dto) {

        // DTO → ENTITY
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());

        // SAVE ENTITY
        Student saved = studentRepository.save(student);

        // ENTITY → RESPONSE DTO
        return new StudentResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getAge()
        );
    }

    @Override
    public Student getStudentById(Long id) {
    	return studentRepository.findById(id)
    	        .orElseThrow(() ->
    	            new StudentNotFoundException("Student not found with id: " + id)
    	        );

    }

    @Override
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                    new StudentNotFoundException(
                        "Student not found with id: " + id));

        // Update fields from DTO
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());

        Student updated = studentRepository.save(student);

        return new StudentResponseDTO(
                updated.getId(),
                updated.getName(),
                updated.getEmail(),
                updated.getAge()
        );
    }


    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
    @Override
    public PagedResponseDTO<StudentResponseDTO> searchByName(
            String name, int page, int size, String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Student> studentPage =
                studentRepository.findByNameContainingIgnoreCase(name, pageable);

        List<StudentResponseDTO> content = new ArrayList<>();
        for (Student s : studentPage.getContent()) {
            content.add(new StudentResponseDTO(
                    s.getId(), s.getName(), s.getEmail(), s.getAge()));
        }

        return new PagedResponseDTO<>(
                content,
                studentPage.getNumber(),
                studentPage.getTotalPages(),
                studentPage.getTotalElements(),
                studentPage.getSize(),
                studentPage.isLast()
        );
    }

    @Override
    public PagedResponseDTO<StudentResponseDTO> searchByEmail(
            String email, int page, int size, String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Student> studentPage =
                studentRepository.findByEmailContainingIgnoreCase(email, pageable);

        List<StudentResponseDTO> content = new ArrayList<>();
        for (Student s : studentPage.getContent()) {
            content.add(new StudentResponseDTO(
                    s.getId(), s.getName(), s.getEmail(), s.getAge()));
        }

        return new PagedResponseDTO<>(
                content,
                studentPage.getNumber(),
                studentPage.getTotalPages(),
                studentPage.getTotalElements(),
                studentPage.getSize(),
                studentPage.isLast()
        );
    }

}


