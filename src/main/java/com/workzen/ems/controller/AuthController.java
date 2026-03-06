package com.workzen.ems.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @RequestMapping("/")
    public String adminDashboard() {
        return "Welcome to Admin Dashboard";
    }

}