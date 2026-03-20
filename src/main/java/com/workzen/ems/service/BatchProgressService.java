package com.workzen.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import com.workzen.ems.entity.Batch;
import com.workzen.ems.entity.BatchProgress;
import com.workzen.ems.entity.Trainer;
import com.workzen.ems.repository.BatchProgressRepository;
import com.workzen.ems.repository.BatchRepository;
import com.workzen.ems.repository.TrainerRepository;

@Service
public class BatchProgressService {

    @Autowired
    private BatchProgressRepository batchProgressRepository;

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    public BatchProgress addBatchProgress(BatchProgress batchProgress, Long batchId, Long trainerId) {
        Batch batch = batchRepository.findById(batchId).orElse(null);
        Trainer trainer = trainerRepository.findById(trainerId).orElse(null);

        batchProgress.setBatch(batch);
        batchProgress.setTrainer(trainer);

        return batchProgressRepository.save(batchProgress);
    }

    public BatchProgress addBatchProgressWithFile(
            String date,
            String topicCovered,
            String notes,
            Integer completionPercentage,
            Long batchId,
            Long trainerId,
            MultipartFile file) throws IOException {
        
        System.out.println("Service: Processing upload - Date: " + date + ", File: " + (file != null ? file.getOriginalFilename() : "None"));
        
        BatchProgress progress = new BatchProgress();
        try {
            progress.setDate(java.time.LocalDate.parse(date));
        } catch (Exception e) {
            System.err.println("Invalid date format: " + date);
            progress.setDate(java.time.LocalDate.now());
        }
        
        progress.setTopicCovered(topicCovered);
        progress.setNotes(notes);
        progress.setCompletionPercentage(completionPercentage);
        
        if (file != null && !file.isEmpty()) {
            progress.setFileName(file.getOriginalFilename());
            progress.setFileType(file.getContentType());
            progress.setFileData(file.getBytes());
            System.out.println("Service: File data attached (" + file.getSize() + " bytes)");
        }
        
        return addBatchProgress(progress, batchId, trainerId);
    }

    public List<BatchProgress> getAllBatchProgress() {
        return batchProgressRepository.findAll();
    }

    public void deleteBatchProgress(Long id) {
        batchProgressRepository.deleteById(id);
    }
    
    public BatchProgress updateBatchProgress(Long id, BatchProgress progressDetails) {
        BatchProgress progress = batchProgressRepository.findById(id).orElse(null);
        if (progress != null) {
            if (progressDetails.getDate() != null) progress.setDate(progressDetails.getDate());
            if (progressDetails.getTopicCovered() != null) progress.setTopicCovered(progressDetails.getTopicCovered());
            if (progressDetails.getNotes() != null) progress.setNotes(progressDetails.getNotes());
            if (progressDetails.getCompletionPercentage() != null) progress.setCompletionPercentage(progressDetails.getCompletionPercentage());
            return batchProgressRepository.save(progress);
        }
        return null;
    }
    
    public List<BatchProgress> getBatchProgressByBatchId(Long batchId) {
        return batchProgressRepository.findByBatchId(batchId);
    }
    
    public List<BatchProgress> getBatchProgressByTrainerId(Long trainerId) {
        return batchProgressRepository.findByTrainerId(trainerId);
    }
}
