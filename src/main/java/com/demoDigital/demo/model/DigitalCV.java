package com.demoDigital.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "digitalcvs")
@Data
public class DigitalCV {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

    @OneToOne
    @JoinColumn(name = "per_id")
    private PersonalInfo personalInfo;

    @OneToMany(mappedBy = "digitalCV")
    private List<Education> educations = new ArrayList<>();

    @OneToMany(mappedBy = "digitalCV")
    private List<Certificate> certificates = new ArrayList<>();

    @OneToMany(mappedBy = "digitalCV")
    private List<Reference> references = new ArrayList<>();

    @OneToMany(mappedBy = "digitalCV")
    private List<ProgrammingLanguage> programmings = new ArrayList<>();

    @OneToMany(mappedBy = "digitalCV")
    private List<WorkExperience> workExperiences = new ArrayList<>();

    @OneToMany(mappedBy = "digitalCV")
    private List<Project> projects = new ArrayList<>();

    @OneToMany(mappedBy = "digitalCV")
    private List<OtherSkills> otherSkills = new ArrayList<>();

    private String hobby;
    private String photo;
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

    public List<Reference> getReferences() {
        return this.references;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }

    public List<ProgrammingLanguage> getProgrammings() {
        return this.programmings;
    }

    public void setProgrammings(List<ProgrammingLanguage> programmings) {
        this.programmings = programmings;
    }

    public List<WorkExperience> getWorkExperiences() {
        return this.workExperiences;
    }

    public void setWorkExperiences(List<WorkExperience> workExperiences) {
        this.workExperiences = workExperiences;
    }

    public List<Project> getProjects() {
        return this.projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<OtherSkills> getOtherSkills() {
        return this.otherSkills;
    }

    public void setOtherSkills(List<OtherSkills> otherSkills) {
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
