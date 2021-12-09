package com.demoDigital.demo.model;

import lombok.Data;

import java.time.LocalDate;

import javax.persistence.*;
import javax.persistence.Id;


@Entity
@Table(name = "certificates")
@Data
public class Certificate {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    DigitalCV digitalCV;

    LocalDate date;
    String certificateName;
    String organizationName;
}
