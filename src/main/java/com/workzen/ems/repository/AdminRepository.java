package com.workzen.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.workzen.ems.entity.Admin;


public interface AdminRepository extends JpaRepository<Admin, Long> {
}