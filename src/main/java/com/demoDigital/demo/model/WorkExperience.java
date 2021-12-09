package com.demoDigital.demo.model;

import lombok.Data;

import java.time.LocalDate;

import javax.persistence.*;
import javax.persistence.Id;


@Entity
@Table(name = "work_experiences")
@Data
public class WorkExperience {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    DigitalCV digitalCV;

    LocalDate fromDate;
    LocalDate toDate;
    String companyName;
    String jobTitle;
    String jobDescription;

}
