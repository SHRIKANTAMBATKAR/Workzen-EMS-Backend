package com.workzen.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workzen.ems.dto.AnalystDashboardStatsDTO;
import com.workzen.ems.entity.Analyst;
import com.workzen.ems.entity.Batch;
import com.workzen.ems.repository.AnalystRepository;
import com.workzen.ems.repository.BatchRepository;
import com.workzen.ems.repository.StudentRepository;
import com.workzen.ems.repository.TrainerRepository;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class AnalystService {

    @Autowired
    private AnalystRepository analystRepository;

    @Autowired
    private BatchRepository batchRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private TrainerRepository trainerRepository;

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
    public AnalystDashboardStatsDTO getDashboardStats() {
        AnalystDashboardStatsDTO stats = new AnalystDashboardStatsDTO();
        stats.setTotalBatches(batchRepository.count());
        stats.setActiveBatches(batchRepository.countByStatus("Active"));
        stats.setTotalStudents(studentRepository.count());
        stats.setUnassignedStudents(studentRepository.countByBatchIsNull());
        stats.setTotalTrainers(trainerRepository.count());

        List<Batch> allBatches = batchRepository.findAll();
        List<AnalystDashboardStatsDTO.BatchSummaryDTO> recentBatches = allBatches.stream()
            .limit(3)
            .map(b -> {
                double progress = 0;
                if ("Active".equalsIgnoreCase(b.getStatus()) && b.getStartDate() != null && b.getEndDate() != null) {
                    long totalDays = ChronoUnit.DAYS.between(b.getStartDate(), b.getEndDate());
                    long elapsedDays = ChronoUnit.DAYS.between(b.getStartDate(), LocalDate.now());
                    if (totalDays > 0) {
                        progress = Math.min(100, Math.max(0, (double) elapsedDays * 100 / totalDays));
                    }
                } else if ("Completed".equalsIgnoreCase(b.getStatus())) {
                    progress = 100;
                }
                
                return new AnalystDashboardStatsDTO.BatchSummaryDTO(
                    b.getId(),
                    b.getBatchName(),
                    b.getStudents() != null ? b.getStudents().size() : 0,
                    b.getCapacity() != null ? b.getCapacity() : 0,
                    Math.round(progress * 10) / 10.0,
                    b.getStatus()
                );
            })
            .collect(Collectors.toList());
        
        stats.setRecentBatches(recentBatches);
        return stats;
    }
}