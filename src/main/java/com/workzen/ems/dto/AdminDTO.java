package com.workzen.ems.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {

    private Long id;

    private String name;

    private String email;

    private String mobile;

    private Boolean active;

}