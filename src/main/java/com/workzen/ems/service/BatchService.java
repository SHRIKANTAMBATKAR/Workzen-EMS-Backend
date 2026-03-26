package com.workzen.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workzen.ems.entity.Analyst;
import com.workzen.ems.entity.Batch;
import com.workzen.ems.entity.BatchProgress;
import com.workzen.ems.entity.Student;
import com.workzen.ems.entity.Trainer;
import com.workzen.ems.repository.*;

@Service
public class BatchService {

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private AnalystRepository analystRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BatchProgressRepository batchProgressRepository;

    public Batch createBatch(Batch batch, Long analystId, Long trainerId) {
        Analyst analyst = analystRepository.findById(analystId).orElse(null);
        Trainer trainer = trainerRepository.findById(trainerId).orElse(null);

        batch.setAnalyst(analyst);
        batch.setTrainer(trainer);

        return batchRepository.save(batch);
    }

    public List<Batch> getAllBatches() {
        return batchRepository.findAll();
    }

    public void deleteBatch(Long id) {
        // 1. Nullify batch reference in students
        List<Student> students = studentRepository.findByBatchId(id);
        for (Student student : students) {
            student.setBatch(null);
            studentRepository.save(student);
        }

        // 2. Delete associated progress records
        List<BatchProgress> progressList = batchProgressRepository.findByBatchId(id);
        if (progressList != null && !progressList.isEmpty()) {
            batchProgressRepository.deleteAll(progressList);
        }

        // 3. Delete the batch
        batchRepository.deleteById(id);
    }
    
    public Batch updateBatch(Long id, Batch batchDetails) {
        Batch batch = batchRepository.findById(id).orElse(null);
        if (batch != null) {
            if (batchDetails.getBatchName() != null) batch.setBatchName(batchDetails.getBatchName());
            if (batchDetails.getCourse() != null) batch.setCourse(batchDetails.getCourse());
            if (batchDetails.getStartDate() != null) batch.setStartDate(batchDetails.getStartDate());
            if (batchDetails.getEndDate() != null) batch.setEndDate(batchDetails.getEndDate());
            if (batchDetails.getCapacity() != null) batch.setCapacity(batchDetails.getCapacity());
            if (batchDetails.getStatus() != null) batch.setStatus(batchDetails.getStatus());
            if (batchDetails.getStartTime() != null) batch.setStartTime(batchDetails.getStartTime());
            if (batchDetails.getDays() != null) batch.setDays(batchDetails.getDays());
            return batchRepository.save(batch);
        }
        return null;
    }
    
    public List<Batch> getBatchesByAnalystId(Long analystId) {
        return batchRepository.findByAnalystId(analystId);
    }
    
    public List<Batch> getBatchesByTrainerId(Long trainerId) {
        return batchRepository.findByTrainerId(trainerId);
    }
}