package com.demoDigital.demo.model;

import lombok.Data;

import java.time.LocalDate;

import javax.persistence.*;
import javax.persistence.Id;


@Entity
@Table(name = "educations")
@Data
public class Education {
    @Id
    Long id;

    @ManyToOne
    DigitalCV digitalCV;

    String name;
    String major;
    LocalDate fromDate;
    LocalDate toDate;
}
