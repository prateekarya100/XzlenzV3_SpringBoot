package com.cybercube.xzlenzv3.model.Compliance;

import java.time.LocalDateTime;
import java.util.List;

import com.cybercube.xzlenzv3.model.User.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "compliance_milestone")
@Data
public class ComplianceMilestone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 10)
    private String milestone; // e.g., "Phase 1 - Documentation Review"

    @Size(max = 2000)
    private String goal;

    @ManyToOne
    private User createdBy;

    @ManyToOne
    private User updatedBy;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    
    @ManyToOne
    @JoinColumn(name = "framework_id", nullable = false)
    private ComplianceFramework framework;

    @OneToMany(mappedBy = "milestone")
    private List<CompliancePoint> points;

    @PreRemove
    private void preRemove() {
        if (points != null) {
            for (CompliancePoint p : points) {
                p.setMilestone(null);
            }
        }
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
