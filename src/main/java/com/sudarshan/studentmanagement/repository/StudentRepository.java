package com.sudarshan.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sudarshan.studentmanagement.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // JpaRepository provides basic CRUD operations
}
