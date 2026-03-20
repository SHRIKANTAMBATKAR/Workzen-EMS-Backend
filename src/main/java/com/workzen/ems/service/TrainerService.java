package com.workzen.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workzen.ems.entity.Batch;
import com.workzen.ems.entity.BatchProgress;
import com.workzen.ems.entity.Trainer;
import com.workzen.ems.repository.BatchProgressRepository;
import com.workzen.ems.repository.BatchRepository;
import com.workzen.ems.repository.TrainerRepository;

@Service
public class TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private BatchProgressRepository batchProgressRepository;

    public Trainer addTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    public List<Trainer> getAllTrainer() {
        return trainerRepository.findAll();
    }

    public void deleteTrainer(Long id) {
        // Nullify trainer reference in batches
        List<Batch> batches = batchRepository.findByTrainerId(id);
        for (Batch b : batches) {
            b.setTrainer(null);
            batchRepository.save(b);
        }

        // Nullify trainer reference in batch progress logs
        List<BatchProgress> logs = batchProgressRepository.findByTrainerId(id);
        for (BatchProgress log : logs) {
            log.setTrainer(null);
            batchProgressRepository.save(log);
        }

        trainerRepository.deleteById(id);
    }

    public Trainer updateTrainer(Long id, Trainer detail) {
        Trainer t = trainerRepository.findById(id).orElse(null);
        if (t != null) {
            if (detail.getName() != null) t.setName(detail.getName());
            if (detail.getEmail() != null) t.setEmail(detail.getEmail());
            if (detail.getMobile() != null) t.setMobile(detail.getMobile());
            if (detail.getPassword() != null) t.setPassword(detail.getPassword());
            if (detail.getJoiningDate() != null) t.setJoiningDate(detail.getJoiningDate());
            if (detail.getPrimarySkill() != null) t.setPrimarySkill(detail.getPrimarySkill());
            if (detail.getExperienceYears() != null) t.setExperienceYears(detail.getExperienceYears());
            if (detail.getQualification() != null) t.setQualification(detail.getQualification());
            return trainerRepository.save(t);
        }
        return null;
    }
}