package com.demoDigital.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Id;


@Entity
@Table(name = "references")
@Data
public class Reference {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    DigitalCV digitalCV;

    String name;
    String email;
    String phone;

}
