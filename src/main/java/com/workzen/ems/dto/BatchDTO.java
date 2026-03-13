package com.workzen.ems.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchDTO {

    private Long id;

    private String batchName;

    private String course;

    private String status;

    private Long analystId;

    private Long trainerId;

}