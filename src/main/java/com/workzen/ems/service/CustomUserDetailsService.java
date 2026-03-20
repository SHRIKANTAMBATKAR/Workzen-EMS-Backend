package com.workzen.ems.service;

import com.workzen.ems.entity.Admin;
import com.workzen.ems.entity.Analyst;
import com.workzen.ems.entity.Counselor;
import com.workzen.ems.entity.Trainer;
import com.workzen.ems.repository.AdminRepository;
import com.workzen.ems.repository.AnalystRepository;
import com.workzen.ems.repository.CounselorRepository;
import com.workzen.ems.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AnalystRepository analystRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private CounselorRepository counselorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        Optional<Admin> admin = adminRepository.findByEmail(email);
        if (admin.isPresent()) {
            return new User(admin.get().getEmail(), admin.get().getPassword(), 
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        }

        Optional<Analyst> analyst = analystRepository.findByEmail(email);
        if (analyst.isPresent()) {
            return new User(analyst.get().getEmail(), analyst.get().getPassword(), 
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ANALYST")));
        }

        Optional<Trainer> trainer = trainerRepository.findByEmail(email);
        if (trainer.isPresent()) {
            return new User(trainer.get().getEmail(), trainer.get().getPassword(), 
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_TRAINER")));
        }

        Optional<Counselor> counselor = counselorRepository.findByEmail(email);
        if (counselor.isPresent()) {
            return new User(counselor.get().getEmail(), counselor.get().getPassword(), 
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_COUNSELOR")));
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
