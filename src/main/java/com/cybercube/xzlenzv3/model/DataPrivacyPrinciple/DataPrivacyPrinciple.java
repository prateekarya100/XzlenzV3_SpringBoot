package com.cybercube.xzlenzv3.model.DataPrivacyPrinciple;

import java.time.LocalDateTime;
import java.util.List;

import com.cybercube.xzlenzv3.model.CCFPoints.CCFPoint;
import com.cybercube.xzlenzv3.model.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
@Table(name = "data_privacy_principles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataPrivacyPrinciple {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Principle name is required")
    @Size(max = 255)
    private String principleName;

    @NotBlank(message = "Principle description is required")
    @Size(max = 3000)
    @Column(length = 3000)
    private String scfDpmpDescription;

    @ManyToOne
    private User createdBy;

	@ManyToOne
    private User updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "dataPrivacyPrinciple", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CCFPrivacyMapping> ccfMappings;

    
    @OneToMany(mappedBy = "dataPrivacyPrinciple", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompliancePrivacyMapping> complianceMappings;



    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
