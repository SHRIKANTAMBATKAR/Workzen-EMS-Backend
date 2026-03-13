package com.workzen.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.workzen.ems.entity.Trainer;
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
}