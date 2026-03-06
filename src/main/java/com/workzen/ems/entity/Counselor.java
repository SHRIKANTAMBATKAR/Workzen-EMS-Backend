package com.workzen.ems.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "counselors")
public class Counselor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String phone;
    
    private Boolean Active;

    private String assignedRegion;
    
    private Integer experienceYears;
    
    private String leadExpertise;

    @OneToMany(mappedBy = "counselor")
    private List<Student> students;

}