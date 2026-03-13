package com.workzen.ems.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchProgressDTO {

    private Long id;

    private LocalDate date;

    private String topicCovered;

    private String notes;

    private Integer completionPercentage;

    private Long batchId;

    private Long trainerId;

}