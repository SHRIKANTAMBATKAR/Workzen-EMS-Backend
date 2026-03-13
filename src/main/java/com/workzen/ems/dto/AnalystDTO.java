package com.workzen.ems.dto;

import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnalystDTO {

    private Long id;

    private String name;

    private String email;

    private String mobile;
    
    private Boolean active;

    private String specialization;

    private Integer experienceYears;

    private String qualification;

    private LocalDate joinDate;


}