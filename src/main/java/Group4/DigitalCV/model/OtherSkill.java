package Group4.DigitalCV.model;

import lombok.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "other_skills")
@Getter
@Setter
@NoArgsConstructor
public class OtherSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private DigitalCV digitalCV;

    private String skill;

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

    public String getSkill() {
        return this.skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public OtherSkill updateModel(OtherSkill curItem, OtherSkill item) {
        curItem.setSkill(item.getSkill());
        return curItem;
    }

}
