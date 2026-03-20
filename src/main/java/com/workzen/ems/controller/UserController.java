package com.workzen.ems.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.workzen.ems.dto.UserDTO;
import com.workzen.ems.entity.Admin;
import com.workzen.ems.entity.Analyst;
import com.workzen.ems.entity.Counselor;
import com.workzen.ems.entity.Trainer;
import com.workzen.ems.service.AdminService;
import com.workzen.ems.service.AnalystService;
import com.workzen.ems.service.CounselorService;
import com.workzen.ems.service.TrainerService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private AdminService adminService;
    
    @Autowired
    private AnalystService analystService;
    
    @Autowired
    private CounselorService counselorService;
    
    @Autowired
    private TrainerService trainerService;

    @GetMapping
    public List<UserDTO> getAllUsers(@RequestParam(required = false) String role) {
        List<UserDTO> users = new ArrayList<>();

        if (role == null || role.equalsIgnoreCase("ADMIN")) {
            for (Admin a : adminService.getAllAdmins()) {
                users.add(new UserDTO(String.valueOf(a.getId()), a.getName(), a.getEmail(), "ADMIN", a.getActive(), a.getMobile()));
            }
        }

        if (role == null || role.equalsIgnoreCase("ANALYST")) {
            for (Analyst a : analystService.getAllAnalysts()) {
                users.add(new UserDTO(String.valueOf(a.getId()), a.getName(), a.getEmail(), "ANALYST", a.getActive(), a.getMobile()));
            }
        }

        if (role == null || role.equalsIgnoreCase("COUNSELOR")) {
            for (Counselor c : counselorService.getAllCounselors()) {
                users.add(new UserDTO(String.valueOf(c.getId()), c.getName(), c.getEmail(), "COUNSELOR", c.getActive(), c.getMobile()));
            }
        }

        if (role == null || role.equalsIgnoreCase("TRAINER")) {
            for (Trainer t : trainerService.getAllTrainer()) {
                users.add(new UserDTO(String.valueOf(t.getId()), t.getName(), t.getEmail(), "TRAINER", t.getActive(), t.getMobile()));
            }
        }

        return users;
    }
}
