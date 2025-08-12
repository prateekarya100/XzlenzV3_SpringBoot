package com.cybercube.xzlenzv3.model.ClientCCFPoint;

import java.time.LocalDateTime;
import java.util.List;

import com.cybercube.xzlenzv3.model.CCFPoints.CCFVersion;
import com.cybercube.xzlenzv3.model.CCFPrinciple.CCFPrinciple;
import com.cybercube.xzlenzv3.model.ClientCompliance.ClientComplianceFramework;
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
@Table(name = "client_ccf_points")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientCCFPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "CCF Number is required")
    @Pattern(regexp = "^[A-Za-z0-9_.-]+$", message = "Invalid SCF Number format")
    @Size(max = 100)
    private String ccfNumber;


    private Boolean inScope;

    @ManyToOne
    private User createdBy;

	@ManyToOne
    private User updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    
    @ManyToOne
    @JoinColumn(name = "ccf_version_id")
    private ClientCCFVersion ccfVersion;
    

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
