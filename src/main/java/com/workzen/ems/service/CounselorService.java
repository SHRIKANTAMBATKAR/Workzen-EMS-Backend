package com.workzen.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workzen.ems.entity.Counselor;
import com.workzen.ems.entity.Trainer;
import com.workzen.ems.repository.CounselorRepository;

@Service
public class CounselorService {

    @Autowired
    private CounselorRepository counselorRepository;

    public Counselor addCounselor(Counselor counselor) {
        return counselorRepository.save(counselor);
    }

    public List<Counselor> getAllCounselors() {
        return counselorRepository.findAll();
    }

    public void deleteCounselor(Long id) {
        counselorRepository.deleteById(id);
    }

	
}