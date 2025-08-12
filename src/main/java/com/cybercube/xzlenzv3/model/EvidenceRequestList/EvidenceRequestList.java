package com.cybercube.xzlenzv3.model.EvidenceRequestList;

import java.time.LocalDateTime;
import java.util.List;

import com.cybercube.xzlenzv3.model.User.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "evidence_request_list")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvidenceRequestList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    


    @NotBlank(message = "ERL number is required")
    @Pattern(regexp = "^[A-Za-z0-9_.-]+$", message = "Invalid ERL number format")
    @Size(max = 100)
    private String erlNumber;

    @NotBlank(message = "Area of focus is required")
    @Size(max = 255)
    private String areaOfFocus;

    @NotBlank(message = "Documentation artifact is required")
    @Size(max = 255)
    private String documentationArtifact;

    @Size(max = 2000)
    private String artifactDescription;

    @ManyToOne
    private User createdBy;

	@ManyToOne
    private User updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    
    
    @OneToMany(mappedBy = "evidenceRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CCFEvidenceMapping> ccfMappings;



    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
