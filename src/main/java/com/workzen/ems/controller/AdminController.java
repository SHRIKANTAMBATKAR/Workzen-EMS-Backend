package com.workzen.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.workzen.ems.entity.*;
import com.workzen.ems.service.*;

@RestController
@RequestMapping("/api/admins")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private AnalystService analystService;

    @Autowired
    private CounselorService counselorService;

    @Autowired
    private TrainerService trainerService;
    
    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.createAdmin(admin);
    }

    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/{id}")
    public Admin getAdmin(@PathVariable Long id) {
        return adminService.getAdminById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return "Admin deleted successfully";
    }
    

    @PostMapping("/analysts")
    public Analyst addAnalyst(@RequestBody Analyst analyst) {
        return analystService.addAnalyst(analyst);
    }

    // Get Analysts
    @GetMapping("/analysts")
    public List<Analyst> getAnalysts() {
        return analystService.getAllAnalysts();
    }

    // Delete Analyst
    @DeleteMapping("/analysts/{id}")
    public String deleteAnalyst(@PathVariable Long id) {
        analystService.deleteAnalyst(id);
        return "Analyst deleted successfully";
    }

    // Add Counselor
    @PostMapping("/counselors")
    public Counselor addCounselor(@RequestBody Counselor counselor) {
        return counselorService.addCounselor(counselor);
    }

    // Get Counselors
    @GetMapping("/counselors")
    public List<Counselor> getCounselors() {
        return counselorService.getAllCounselors();
    }

    // Delete Counselor
    @DeleteMapping("/counselors/{id}")
    public String deleteCounselor(@PathVariable Long id) {
        counselorService.deleteCounselor(id);
        return "Counselor deleted successfully";
    }
    
 // Add Counselor
    @PostMapping("/trainers")
    public Trainer addTrainer(@RequestBody Trainer trainer) {
        return trainerService.addTrainer(trainer);
    }

    // Get Counselors
    @GetMapping("/trainers")
    public List<Trainer> getTrainer() {
        return trainerService.getAllTrainer();
    }

    // Delete Counselor
    @DeleteMapping("/trainers/{id}")
    public String deleteTrainer(@PathVariable Long id) {
        counselorService.deleteCounselor(id);
        return "Counselor deleted successfully";
    }
    
}