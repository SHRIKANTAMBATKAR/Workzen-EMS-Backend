package com.workzen.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.workzen.ems.entity.*;

import java.util.List;

public interface BatchRepository extends JpaRepository<Batch, Long> {
    List<Batch> findByAnalystId(Long analystId);
    List<Batch> findByTrainerId(Long trainerId);
}