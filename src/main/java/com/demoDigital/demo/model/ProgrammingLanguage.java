package com.demoDigital.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Id;

@Entity
@Table(name = "programming_languages")
@Data
public class ProgrammingLanguage {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    DigitalCV digitalCV;

    String technicalSkillset;
    String competence;
    Level level;
}
