package com.workzen.ems.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.workzen.ems.entity.Counselor;

public interface CounselorRepository extends JpaRepository<Counselor, Long> {
    Optional<Counselor> findByEmail(String email);
}