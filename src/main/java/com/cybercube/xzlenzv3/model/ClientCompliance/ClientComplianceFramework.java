package com.cybercube.xzlenzv3.model.ClientCompliance;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.cybercube.xzlenzv3.model.ClientProject.ClientProject;
import com.cybercube.xzlenzv3.model.Compliance.ComplianceFramework;
import com.cybercube.xzlenzv3.model.User.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "client_compliance_frameworks")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ClientComplianceFramework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(unique = true)
    private String name; // e.g., "PCI DSS", "ISO 27001", "GDPR"

    // NEW: validity window for this framework in the project
    private LocalDate startDate;
    private LocalDate endDate;

    // IMPORTANT: back-reference to satisfy ClientProject.compliances (mappedBy="project")
    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private ClientProject project;

    @ManyToOne private User createdBy;
    @ManyToOne private User updatedBy;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "framework", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClientCompliancePoint> points;

    @ManyToOne
    @JoinColumn(name = "framework_id", nullable = false)
    private ComplianceFramework framework;

    @PrePersist protected void onCreate() { this.createdAt = LocalDateTime.now(); }
    @PreUpdate  protected void onUpdate() { this.updatedAt = LocalDateTime.now(); }
}
