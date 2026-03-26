package com.workzen.ems.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalystDashboardStatsDTO {
    private long totalBatches;
    private long activeBatches;
    private long totalStudents;
    private long unassignedStudents;
    private long totalTrainers;
    private List<BatchSummaryDTO> recentBatches;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BatchSummaryDTO {
        private Long id;
        private String name;
        private int studentCount;
        private int capacity;
        private double progress;
        private String status;
    }
}
