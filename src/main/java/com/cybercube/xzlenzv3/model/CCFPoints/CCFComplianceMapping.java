package com.cybercube.xzlenzv3.model.CCFPoints;

import java.time.LocalDateTime;

import com.cybercube.xzlenzv3.model.Compliance.CompliancePoint;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ccf_compliance_mapping",
       uniqueConstraints = @UniqueConstraint(columnNames = {"ccf_point_id", "compliance_point_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CCFComplianceMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ccf_point_id", nullable = false)
    private CCFPoint ccfPoint;

    @ManyToOne
    @JoinColumn(name = "compliance_point_id", nullable = false)
    private CompliancePoint compliancePoint;

    private String note;

    private LocalDateTime mappedAt;
}
