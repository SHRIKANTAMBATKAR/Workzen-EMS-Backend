package com.workzen.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workzen.ems.entity.Batch;
import com.workzen.ems.entity.Counselor;
import com.workzen.ems.entity.Student;
import com.workzen.ems.repository.BatchRepository;
import com.workzen.ems.repository.CounselorRepository;
import com.workzen.ems.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CounselorRepository counselorRepository;

    @Autowired
    private BatchRepository batchRepository;

    public Student addStudent(Student student, Long counselorId, Long batchId) {
        Counselor counselor = counselorRepository.findById(counselorId).orElse(null);
        student.setCounselor(counselor);

        if (batchId != null) {
            Batch batch = batchRepository.findById(batchId).orElse(null);
            student.setBatch(batch);
        }

        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(Long id, Student studentDetails, Long batchId) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            if (studentDetails.getName() != null) student.setName(studentDetails.getName());
            if (studentDetails.getEmail() != null) student.setEmail(studentDetails.getEmail());
            if (studentDetails.getPhone() != null) student.setPhone(studentDetails.getPhone());
            if (studentDetails.getCourse() != null) student.setCourse(studentDetails.getCourse());
            if (studentDetails.getStatus() != null) student.setStatus(studentDetails.getStatus());
            if (studentDetails.getEnrolmentDate() != null) student.setEnrolmentDate(studentDetails.getEnrolmentDate());
            if (studentDetails.getPerformance() != null) student.setPerformance(studentDetails.getPerformance());
            
            if (batchId != null) {
                Batch batch = batchRepository.findById(batchId).orElse(null);
                student.setBatch(batch);
            }
            return studentRepository.save(student);
        }
        return null;
    }
    
    public List<Student> getStudentsByCounselorId(Long counselorId) {
        return studentRepository.findByCounselorId(counselorId);
    }
    
    public List<Student> getStudentsByBatchId(Long batchId) {
        return studentRepository.findByBatchId(batchId);
    }
}