package com.demoDigital.demo.model;

import lombok.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "digitalcvs")
@Getter
@Setter
@NoArgsConstructor
public class DigitalCV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private PersonalInfo personalInfo;

    @OneToMany(mappedBy = "digitalCV", cascade = CascadeType.ALL)
    private List<Education> educations = new ArrayList<>();

    @OneToMany(mappedBy = "digitalCV", cascade = CascadeType.ALL)
    private List<Certificate> certificates = new ArrayList<>();

    @OneToMany(mappedBy = "digitalCV", cascade = CascadeType.ALL)
    private List<ReferenceCV> referencecvs = new ArrayList<>();

    @OneToMany(mappedBy = "digitalCV", cascade = CascadeType.ALL)
    private List<ProgrammingLanguage> programmingLanguages = new ArrayList<>();

    @OneToMany(mappedBy = "digitalCV", cascade = CascadeType.ALL)
    private List<WorkingExperience> workingExperiences = new ArrayList<>();

    @OneToMany(mappedBy = "digitalCV", cascade = CascadeType.ALL)
    private List<Project> projects = new ArrayList<>();

    @OneToMany(mappedBy = "digitalCV", cascade = CascadeType.ALL)
    private List<OtherSkill> otherSkills = new ArrayList<>();

    // @ManyToOne(cascade = CascadeType.ALL)
    // private ImageModel photo;
    private String photo;

    private String hobby;
    private String jobTitle;
    private String cvType;
    private Boolean isActive;

    public Boolean isIsActive() {
        return this.isActive;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonalInfo getPersonalInfo() {
        return this.personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public List<Education> getEducations() {
        return this.educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public List<Certificate> getCertificates() {
        return this.certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }

    public List<ReferenceCV> getReferencecvs() {
        return this.referencecvs;
    }

    public void setReferencecvs(List<ReferenceCV> referencecvs) {
        this.referencecvs = referencecvs;
    }

    public List<ProgrammingLanguage> getProgrammingLanguages() {
        return this.programmingLanguages;
    }

    public void setProgrammingLanguages(List<ProgrammingLanguage> programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }

    public List<WorkingExperience> getWorkingExperiences() {
        return this.workingExperiences;
    }

    public void setWorkingExperiences(List<WorkingExperience> workingExperiences) {
        this.workingExperiences = workingExperiences;
    }

    public List<Project> getProjects() {
        return this.projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<OtherSkill> getOtherSkills() {
        return this.otherSkills;
    }

    public void setOtherSkills(List<OtherSkill> otherSkills) {
        this.otherSkills = otherSkills;
    }

    public String getHobby() {
        return this.hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCvType() {
        return this.cvType;
    }

    public void setCvType(String cvType) {
        this.cvType = cvType;
    }
}
