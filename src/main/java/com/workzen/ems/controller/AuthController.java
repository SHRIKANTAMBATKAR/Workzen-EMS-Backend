package com.workzen.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.workzen.ems.dto.LoginRequestDTO;
import com.workzen.ems.dto.LoginResponseDTO;
import com.workzen.ems.entity.Admin;
import com.workzen.ems.service.AdminService;
import com.workzen.ems.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {
        return authService.login(request);
    }

    // ⚠️ TEMPORARY ENDPOINT - Remove after creating the first admin
    @PostMapping("/register-admin")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) {
        admin.setActive(true);
        Admin saved = adminService.createAdmin(admin);
        return ResponseEntity.ok(saved);
    }
}