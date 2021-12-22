package Group4.DigitalCV.customModel;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ManagementCV {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime lastModified;
    private String cvType;

    public ManagementCV(Long id, LocalDateTime createdAt, LocalDateTime lastModified, String cvType) {
        this.id = id;
        this.createdAt = createdAt;
        this.lastModified = lastModified;
        this.cvType = cvType;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastModified() {
        return this.lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public String getCvType() {
        return this.cvType;
    }

    public void setCvType(String cvType) {
        this.cvType = cvType;
    }

}
