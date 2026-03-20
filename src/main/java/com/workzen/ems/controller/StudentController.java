package com.workzen.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.workzen.ems.entity.Student;
import com.workzen.ems.service.StudentService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin("*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public Student addStudent(
            @RequestBody Student student,
            @RequestParam Long counselorId,
            @RequestParam(required = false) Long batchId) {
        return studentService.addStudent(student, counselorId, batchId);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "Student deleted successfully";
    }

    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable Long id, 
            @RequestBody Student studentDetails,
            @RequestParam(required = false) Long batchId) {
        return studentService.updateStudent(id, studentDetails, batchId);
    }
    
    @GetMapping("/counselor/{counselorId}")
    public List<Student> getStudentsByCounselorId(@PathVariable Long counselorId) {
        return studentService.getStudentsByCounselorId(counselorId);
    }
    
    @GetMapping("/batch/{batchId}")
    public List<Student> getStudentsByBatchId(@PathVariable Long batchId) {
        return studentService.getStudentsByBatchId(batchId);
    }
}