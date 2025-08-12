package com.cybercube.xzlenzv3.model.ClientProject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.cybercube.xzlenzv3.model.Client.Client;
import com.cybercube.xzlenzv3.model.ClientCCFPoint.ClientCCFVersion;
import com.cybercube.xzlenzv3.model.ClientCompliance.ClientComplianceFramework;
import com.cybercube.xzlenzv3.model.User.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "client_project")
@Data
public class ClientProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Client client;

    @NotBlank
    @Size(max = 120)
    private String name;

    @Size(max = 1000)
    private String description;

    @ManyToOne private User createdBy;
    @ManyToOne private User updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /** Multiple CCF versions for the project */
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClientCCFVersion> ccfVersions;

    /** Multiple Compliance Frameworks for the project */
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClientComplianceFramework> compliances;

    @PrePersist void onCreate() { this.createdAt = LocalDateTime.now(); }
    @PreUpdate  void onUpdate() { this.updatedAt = LocalDateTime.now(); }
}
