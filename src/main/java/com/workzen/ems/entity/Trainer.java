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
@Table(name = "trainers")
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String phone;
    
    private Boolean active;
    
    private LocalDate joiningDate;
    
    private String primarySkill;
    
    private Integer experienceYears;

    private String qualification;

    @OneToMany(mappedBy = "trainer")
    private List<Batch> batches;

}