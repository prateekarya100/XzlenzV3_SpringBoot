package com.cybercube.xzlenzv3.model.CCFPoints;

import java.time.LocalDateTime;
import java.util.List;

import com.cybercube.xzlenzv3.model.CCFPrinciple.CCFPrinciple;
import com.cybercube.xzlenzv3.model.DataPrivacyPrinciple.CCFPrivacyMapping;
import com.cybercube.xzlenzv3.model.EvidenceRequestList.CCFEvidenceMapping;
import com.cybercube.xzlenzv3.model.RiskCatalog.CCFRiskMapping;
import com.cybercube.xzlenzv3.model.ThreatCatalog.CCFThreatMapping;
import com.cybercube.xzlenzv3.model.User.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ccf_points")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CCFPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    

    @NotBlank(message = "CCF Domain is required")
    @Size(max = 255)
    private String ccfDomain;

    @NotBlank(message = "CCF Control is required")
    @Size(max = 255)
    private String ccfControl;

    @NotBlank(message = "CCF Number is required")
    @Pattern(regexp = "^[A-Za-z0-9_.-]+$", message = "Invalid SCF Number format")
    @Size(max = 100)
    private String ccfNumber;

    @NotBlank(message = "CCF Control Description is required")
    @Size(max = 2000)
    @Column(length = 2000)
    private String ccfControlDescription;

    @Size(max = 2000)
    private String ccfControlQuestion;

    @DecimalMin(value = "0.0", inclusive = true, message = "Weight must be 0 or more")
    @DecimalMax(value = "10.0", inclusive = true, message = "Weight must be less than or equal to 10")
    private Double relativeControlWeighting;

    private Boolean applicable;

    @ManyToOne
    private User createdBy;

	@ManyToOne
    private User updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    
    @ManyToOne
    @JoinColumn(name = "ccf_version_id")
    private CCFVersion ccfVersion;

    @ManyToOne
    @JoinColumn(name = "ccf_principle_id")
    private CCFPrinciple ccfPrinciple;

    
    @OneToMany(mappedBy = "ccfPoint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CCFEvidenceMapping> evidenceMappings;

    @OneToMany(mappedBy = "ccfPoint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CCFPrivacyMapping> privacyMappings;

    @OneToMany(mappedBy = "ccfPoint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CCFRiskMapping> riskMappings;

    @OneToMany(mappedBy = "ccfPoint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CCFThreatMapping> threatMappings;




    @OneToMany(mappedBy = "ccfPoint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CCFComplianceMapping> complianceMappings;

    

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
