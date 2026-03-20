package com.workzen.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.workzen.ems.entity.BatchProgress;
import com.workzen.ems.service.BatchProgressService;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/api/sessionLogs")
@CrossOrigin("*")
public class BatchProgressController {

    @Autowired
    private BatchProgressService batchProgressService;

    @PostMapping
    public BatchProgress addBatchProgress(
            @RequestBody BatchProgress batchProgress,
            @RequestParam Long batchId,
            @RequestParam Long trainerId) {
        return batchProgressService.addBatchProgress(batchProgress, batchId, trainerId);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BatchProgress addBatchProgressWithFile(
            @RequestParam("date") String date,
            @RequestParam("topicCovered") String topicCovered,
            @RequestParam("notes") String notes,
            @RequestParam("completionPercentage") Integer completionPercentage,
            @RequestParam("batchId") Long batchId,
            @RequestParam("trainerId") Long trainerId,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            System.out.println("Received upload request for Batch: " + batchId + " by Trainer: " + trainerId);
            return batchProgressService.addBatchProgressWithFile(
                    date, topicCovered, notes, completionPercentage, batchId, trainerId, file);
        } catch (Exception e) {
            System.err.println("Error during batch progress upload: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Upload failed: " + e.getMessage());
        }
    }

    @GetMapping
    public List<BatchProgress> getAllBatchProgress() {
        return batchProgressService.getAllBatchProgress();
    }

    @DeleteMapping("/{id}")
    public String deleteBatchProgress(@PathVariable Long id) {
        batchProgressService.deleteBatchProgress(id);
        return "Batch progress deleted successfully";
    }

    @PutMapping("/{id}")
    public BatchProgress updateBatchProgress(@PathVariable Long id, @RequestBody BatchProgress progressDetails) {
        return batchProgressService.updateBatchProgress(id, progressDetails);
    }
    
    @GetMapping("/batch/{batchId}")
    public List<BatchProgress> getBatchProgressByBatchId(@PathVariable Long batchId) {
        return batchProgressService.getBatchProgressByBatchId(batchId);
    }
    
    @GetMapping("/trainer/{trainerId}")
    public List<BatchProgress> getBatchProgressByTrainerId(@PathVariable Long trainerId) {
        return batchProgressService.getBatchProgressByTrainerId(trainerId);
    }
}