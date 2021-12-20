package Group4.DigitalCV.model;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Id;

import Group4.DigitalCV.customModel.Level;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "programming_languages")
@Getter
@Setter
@NoArgsConstructor
public class ProgrammingLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private DigitalCV digitalCV;

    private String technicalSkillset;
    private String competence;
    private Level level;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DigitalCV getDigitalCV() {
        return this.digitalCV;
    }

    public void setDigitalCV(DigitalCV digitalCV) {
        this.digitalCV = digitalCV;
    }

    public String getTechnicalSkillset() {
        return this.technicalSkillset;
    }

    public void setTechnicalSkillset(String technicalSkillset) {
        this.technicalSkillset = technicalSkillset;
    }

    public String getCompetence() {
        return this.competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public Level getLevel() {
        return this.level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public ProgrammingLanguage updateModel(ProgrammingLanguage curItem, ProgrammingLanguage item) {
        curItem.setTechnicalSkillset(item.getTechnicalSkillset());
        curItem.setCompetence(item.getCompetence());
        curItem.setLevel(item.getLevel());
        return curItem;
    }

}
