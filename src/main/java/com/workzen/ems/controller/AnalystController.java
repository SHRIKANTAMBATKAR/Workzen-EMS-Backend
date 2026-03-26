package com.workzen.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.workzen.ems.dto.AnalystDashboardStatsDTO;
import com.workzen.ems.service.AnalystService;

@RestController
@RequestMapping("/api/analysts")
@CrossOrigin(origins = "*")
public class AnalystController {

    @Autowired
    private AnalystService analystService;

    @GetMapping("/stats")
    public AnalystDashboardStatsDTO getDashboardStats() {
        return analystService.getDashboardStats();
    }
}
