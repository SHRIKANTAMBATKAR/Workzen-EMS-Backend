package com.workzen.ems.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.workzen.ems.config.JwtUtils;
import com.workzen.ems.dto.LoginRequestDTO;
import com.workzen.ems.dto.LoginResponseDTO;
import com.workzen.ems.entity.Admin;
import com.workzen.ems.entity.Analyst;
import com.workzen.ems.entity.Counselor;
import com.workzen.ems.entity.Trainer;
import com.workzen.ems.repository.AdminRepository;
import com.workzen.ems.repository.AnalystRepository;
import com.workzen.ems.repository.CounselorRepository;
import com.workzen.ems.repository.TrainerRepository;

@Service
public class AuthService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AnalystRepository analystRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private CounselorRepository counselorRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    public LoginResponseDTO login(LoginRequestDTO request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (Exception e) {
            return new LoginResponseDTO(null, null, null, null, false, "Invalid email or password", null);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        final String jwt = jwtUtils.generateToken(userDetails);

        // Map correct role-specific details to the response
        Optional<Admin> adminOpt = adminRepository.findByEmail(request.getEmail());
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            return new LoginResponseDTO(admin.getId(), admin.getName(), admin.getEmail(), "ADMIN", admin.getActive(), "Login successful", jwt);
        }

        Optional<Analyst> analystOpt = analystRepository.findByEmail(request.getEmail());
        if (analystOpt.isPresent()) {
            Analyst analyst = analystOpt.get();
            return new LoginResponseDTO(analyst.getId(), analyst.getName(), analyst.getEmail(), "ANALYST", analyst.getActive(), "Login successful", jwt);
        }

        Optional<Trainer> trainerOpt = trainerRepository.findByEmail(request.getEmail());
        if (trainerOpt.isPresent()) {
            Trainer trainer = trainerOpt.get();
            return new LoginResponseDTO(trainer.getId(), trainer.getName(), trainer.getEmail(), "TRAINER", trainer.getActive(), "Login successful", jwt);
        }

        Optional<Counselor> counselorOpt = counselorRepository.findByEmail(request.getEmail());
        if (counselorOpt.isPresent()) {
            Counselor counselor = counselorOpt.get();
            return new LoginResponseDTO(counselor.getId(), counselor.getName(), counselor.getEmail(), "COUNSELOR", counselor.getActive(), "Login successful", jwt);
        }

        return new LoginResponseDTO(null, null, null, null, false, "User details not found", null);
    }
}