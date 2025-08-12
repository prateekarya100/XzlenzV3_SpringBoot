package com.cybercube.xzlenzv3.model.CCFPrinciple;

import java.time.LocalDateTime;
import java.util.List;

import com.cybercube.xzlenzv3.model.CCFPoints.CCFPoint;
import com.cybercube.xzlenzv3.model.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "ccf_principles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CCFPrinciple {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "CCF Domain is required")
    @Size(max = 255)
    private String ccfDomain;

    @NotBlank(message = "CCF Identifier is required")
    @Size(max = 100)
    private String ccfIdentifier;

    @NotBlank(message = "C|P Principle is required")
    @Size(max = 500)
    private String cpPrinciple;

    @NotBlank(message = "Principle Intent is required")
    @Size(max = 2000)
    private String principleIntent;

    @ManyToOne
    private User createdBy;

	@ManyToOne
    private User updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    
    @JsonIgnore
    @OneToMany(mappedBy = "ccfPrinciple")
    private List<CCFPoint> ccfPoints;


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
