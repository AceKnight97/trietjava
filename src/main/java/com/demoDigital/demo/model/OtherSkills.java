package com.demoDigital.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "other_skills")
@Data
public class OtherSkills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private DigitalCV digitalCV;

    private String skill;
}
