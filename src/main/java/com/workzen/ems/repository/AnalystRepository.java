package com.workzen.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.workzen.ems.entity.Analyst;

public interface AnalystRepository extends JpaRepository<Analyst, Long> {

}