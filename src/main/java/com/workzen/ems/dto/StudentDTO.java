package com.workzen.ems.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String course;

    private String status;

    private Long counselorId;

    private Long batchId;

}