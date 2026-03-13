package com.workzen.ems.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CounselorDTO {

    private Long id;

    private String name;

    private String email;

    private String mobile;

    private Boolean active;
    
   private String assignedRegion;
    
    private Integer experienceYears;
    
    private String leadExpertise;

}