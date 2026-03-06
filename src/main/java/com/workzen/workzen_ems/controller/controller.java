package com.workzen.workzen_ems.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {

    @RequestMapping("/")
    public String adminDashboard() {
        return "Welcome to Admin Dashboard";
    }

}