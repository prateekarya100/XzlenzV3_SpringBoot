package com.cybercube.xzlenzv3.model.ClientCompliance;

import java.time.LocalDateTime;
import java.util.List;

import com.cybercube.xzlenzv3.model.CCFPoints.CCFComplianceMapping;
import com.cybercube.xzlenzv3.model.DataPrivacyPrinciple.CompliancePrivacyMapping;
import com.cybercube.xzlenzv3.model.User.User;

import jakarta.persistence.CascadeType;
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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "client_compliance_points")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientCompliancePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String controlNumber; // e.g., "A.5.1.1", "3.2.1"
    
    private Boolean inScope;
    

    @ManyToOne
    private User createdBy;

	@ManyToOne
    private User updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    
    @ManyToOne
    @JoinColumn(name = "framework_id", nullable = false)
    private ClientComplianceFramework framework;


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
