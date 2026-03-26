package com.workzen.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workzen.ems.entity.Counselor;
import com.workzen.ems.entity.Student;
import com.workzen.ems.repository.CounselorRepository;
import com.workzen.ems.repository.StudentRepository;

@Service
public class CounselorService {

    @Autowired
    private CounselorRepository counselorRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Counselor addCounselor(Counselor counselor) {
        return counselorRepository.save(counselor);
    }

    public List<Counselor> getAllCounselors() {
        return counselorRepository.findAll();
    }

    public void deleteCounselor(Long id) {
        // Nullify counselor reference in students
        List<Student> students = studentRepository.findByCounselorId(id);
        for (Student s : students) {
            s.setCounselor(null);
            studentRepository.save(s);
        }
        counselorRepository.deleteById(id);
    }

    public Counselor updateCounselor(Long id, Counselor detail) {
        Counselor c = counselorRepository.findById(id).orElse(null);
        if (c != null) {
            if (detail.getName() != null)           c.setName(detail.getName());
            if (detail.getEmail() != null)          c.setEmail(detail.getEmail());
            if (detail.getMobile() != null)         c.setMobile(detail.getMobile());
            if (detail.getPassword() != null)       c.setPassword(detail.getPassword());
            if (detail.getAssignedRegion() != null) c.setAssignedRegion(detail.getAssignedRegion());
            if (detail.getExperienceYears() != null) c.setExperienceYears(detail.getExperienceYears());
            // Note: Counselor entity has no 'qualification' field — removed that line
            return counselorRepository.save(c);
        }
        return null;
    }

}