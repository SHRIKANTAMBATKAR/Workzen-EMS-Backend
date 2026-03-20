package com.workzen.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.workzen.ems.entity.*;

import java.util.List;

public interface BatchProgressRepository extends JpaRepository<BatchProgress, Long> {
    List<BatchProgress> findByBatchId(Long batchId);
    List<BatchProgress> findByTrainerId(Long trainerId);
}
