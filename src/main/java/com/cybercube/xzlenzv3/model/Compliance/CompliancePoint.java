package com.cybercube.xzlenzv3.model.Compliance;

import java.time.LocalDateTime;
import java.util.List;

import com.cybercube.xzlenzv3.model.CCFPoints.CCFComplianceMapping;
import com.cybercube.xzlenzv3.model.DataPrivacyPrinciple.CompliancePrivacyMapping;
import com.cybercube.xzlenzv3.model.User.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "compliance_points")
@Data
public class CompliancePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String controlNumber; // e.g., "A.5.1.1", "3.2.1"

    @NotBlank
    @Size(max = 2000)
    private String description;
    
    @ManyToOne
    private User createdBy;

	@ManyToOne
    private User updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "framework_id", nullable = false)
    private ComplianceFramework framework;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "milestone_id", nullable = true)
    private ComplianceMilestone milestone;


    @OneToMany(mappedBy = "compliancePoint", cascade = CascadeType.ALL)
    private List<CCFComplianceMapping> mappings;
    
    @OneToMany(mappedBy = "compliancePoint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompliancePrivacyMapping> privacyMappings;


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
    
    
    

}
