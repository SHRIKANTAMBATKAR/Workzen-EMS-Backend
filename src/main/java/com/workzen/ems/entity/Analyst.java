package com.workzen.ems.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "analysts")
public class Analyst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String role;

    private String mobile;

    private Boolean active;

    private String specialization;

    private Integer experienceYears;

    private String qualification;

    private LocalDate joinDate;

    @OneToMany(mappedBy = "analyst")
    private List<Batch> batches;
}