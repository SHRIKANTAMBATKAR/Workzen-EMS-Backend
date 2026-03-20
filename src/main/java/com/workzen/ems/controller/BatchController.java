package com.workzen.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.workzen.ems.entity.Batch;
import com.workzen.ems.service.BatchService;

@RestController
@RequestMapping("/api/batches")
@CrossOrigin("*")
public class BatchController {

    @Autowired
    private BatchService batchService;

    @PostMapping
    public Batch createBatch(
            @RequestBody Batch batch,
            @RequestParam Long analystId,
            @RequestParam Long trainerId) {
        return batchService.createBatch(batch, analystId, trainerId);
    }

    @GetMapping
    public List<Batch> getAllBatches() {
        return batchService.getAllBatches();
    }

    @DeleteMapping("/{id}")
    public String deleteBatch(@PathVariable Long id) {
        batchService.deleteBatch(id);
        return "Batch deleted successfully";
    }

    @PutMapping("/{id}")
    public Batch updateBatch(@PathVariable Long id, @RequestBody Batch batchDetails) {
        return batchService.updateBatch(id, batchDetails);
    }
    
    @GetMapping("/analyst/{analystId}")
    public List<Batch> getBatchesByAnalystId(@PathVariable Long analystId) {
        return batchService.getBatchesByAnalystId(analystId);
    }
    
    @GetMapping("/trainer/{trainerId}")
    public List<Batch> getBatchesByTrainerId(@PathVariable Long trainerId) {
        return batchService.getBatchesByTrainerId(trainerId);
    }
}