package com.cybercube.xzlenzv3.model.ThreatCatalog;

import java.time.LocalDateTime;
import java.util.List;

import com.cybercube.xzlenzv3.model.User.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "threat_catalog")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThreatCatalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Threat Grouping is required")
    @Size(max = 255)
    private String threatGrouping;

    @NotBlank(message = "Threat Number is required")
    @Size(max = 100)
    private String threatNumber;

    @NotBlank(message = "Threat title is required")
    @Size(max = 2000)
    private String threatTitle;

    @NotBlank(message = "Threat description is required")
    @Size(max = 3000)
    @Column(length = 3000)
    private String threatDescription;

    @ManyToOne
    private User createdBy;

	@ManyToOne
    private User updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "threat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CCFThreatMapping> ccfMappings;


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
