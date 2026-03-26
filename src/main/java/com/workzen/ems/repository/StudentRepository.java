package com.workzen.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.workzen.ems.entity.*;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByCounselorId(Long counselorId);
    List<Student> findByBatchId(Long batchId);
    long countByBatchIsNull();
}