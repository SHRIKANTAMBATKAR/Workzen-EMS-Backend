package com.workzen.ems.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainerDTO {

    private Long id;

    private String name;

    private String email;

    private String mobile;

    private String expertise;

    private Boolean active;

}