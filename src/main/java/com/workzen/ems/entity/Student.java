package com.workzen.ems.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String course;

    private String status;
        
    private LocalDate enrolmentDate;
    
    private String performance;
    

    public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getCourse() {
		return course;
	}

	public String getStatus() {
		return status;
	}

	public LocalDate getEnrolmentDate() {
		return enrolmentDate;
	}

	public String getPerformance() {
		return performance;
	}

	public Counselor getCounselor() {
		return counselor;
	}

	public Batch getBatch() {
		return batch;
	}

	@ManyToOne
    @JoinColumn(name = "counselor_id")
    private Counselor counselor;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

}