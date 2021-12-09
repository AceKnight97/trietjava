package com.demoDigital.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Id;


@Entity
@Table(name = "projects")
@Data
public class Project {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    DigitalCV digitalCV;

    String name;
    String languages;
    String description;
}
