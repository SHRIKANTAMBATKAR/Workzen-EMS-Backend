package com.workzen.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workzen.ems.entity.Analyst;
import com.workzen.ems.entity.Batch;
import com.workzen.ems.repository.AnalystRepository;
import com.workzen.ems.repository.BatchRepository;

@Service
public class AnalystService {

    @Autowired
    private AnalystRepository analystRepository;

    @Autowired
    private BatchRepository batchRepository;

    public Analyst addAnalyst(Analyst analyst) {
        return analystRepository.save(analyst);
    }

    public List<Analyst> getAllAnalysts() {
        return analystRepository.findAll();
    }

    public void deleteAnalyst(Long id) {
        // Nullify analyst reference in batches before deletion
        List<Batch> batches = batchRepository.findByAnalystId(id);
        for (Batch b : batches) {
            b.setAnalyst(null);
            batchRepository.save(b);
        }
        analystRepository.deleteById(id);
    }

    public Analyst updateAnalyst(Long id, Analyst detail) {
        Analyst a = analystRepository.findById(id).orElse(null);
        if (a != null) {
            if (detail.getName() != null)             a.setName(detail.getName());
            if (detail.getEmail() != null)            a.setEmail(detail.getEmail());
            if (detail.getMobile() != null)           a.setMobile(detail.getMobile());
            if (detail.getPassword() != null)         a.setPassword(detail.getPassword());
            if (detail.getSpecialization() != null)   a.setSpecialization(detail.getSpecialization());
            if (detail.getExperienceYears() != null)  a.setExperienceYears(detail.getExperienceYears());
            if (detail.getQualification() != null)    a.setQualification(detail.getQualification());
            if (detail.getJoinDate() != null)         a.setJoinDate(detail.getJoinDate());
            return analystRepository.save(a);
        }
        return null;
    }
}