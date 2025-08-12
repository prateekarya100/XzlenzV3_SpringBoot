package com.cybercube.xzlenzv3.model.ClientCCFPoint;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.cybercube.xzlenzv3.model.ClientProject.ClientProject;
import com.cybercube.xzlenzv3.model.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "client_ccf_versions")
@Data
public class ClientCCFVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String versionName;

    // NEW: validity window for this CCF version in the project
    private LocalDate startDate;
    private LocalDate endDate;

    // IMPORTANT: back-reference to satisfy ClientProject.ccfVersions (mappedBy="project")
    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private ClientProject project;

    @ManyToOne private User createdBy;
    @ManyToOne private User updatedBy;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "ccfVersion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClientCCFPoint> ccfPoints;

    // REMOVE the old self-reference field:
    // @ManyToOne @JoinColumn(name = "ccf_version_id") private ClientCCFVersion ccfVersion;

    @PrePersist protected void onCreate() { this.createdAt = LocalDateTime.now(); }
    @PreUpdate  protected void onUpdate() { this.updatedAt = LocalDateTime.now(); }
}
