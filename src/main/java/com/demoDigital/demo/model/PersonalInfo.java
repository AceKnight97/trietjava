package com.demoDigital.demo.model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

@Entity
@Table(name = "personal_info")
@Getter
@Setter
@NoArgsConstructor
public class PersonalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "personalInfo", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<DigitalCV> digitalcvs = new ArrayList<>();
    private String email;
    private String phone;
    private String address;
    private Gender gender;
    private LocalDate dob;
    private String linkedin;
    private String careerObjective;
    private String username;
    private String password;

    public List<DigitalCV> getDigitalcvs() {
    return this.digitalcvs;
    }

    public void setDigitalcvs(List<DigitalCV> digitalcvs) {
    this.digitalcvs = digitalcvs;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getLinkedin() {
        return this.linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getCareerObjective() {
        return this.careerObjective;
    }

    public void setCareerObjective(String careerObjective) {
        this.careerObjective = careerObjective;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
