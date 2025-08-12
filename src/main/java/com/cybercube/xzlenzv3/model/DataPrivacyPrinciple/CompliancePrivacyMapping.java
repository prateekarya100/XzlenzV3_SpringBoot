package com.cybercube.xzlenzv3.model.DataPrivacyPrinciple;

import com.cybercube.xzlenzv3.model.Compliance.CompliancePoint;
import com.cybercube.xzlenzv3.model.User.User;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "compliance_privacy_mapping",
       uniqueConstraints = @UniqueConstraint(columnNames = {"data_privacy_id", "compliance_point_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompliancePrivacyMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "data_privacy_id", nullable = false)
    private DataPrivacyPrinciple dataPrivacyPrinciple;

    @ManyToOne
    @JoinColumn(name = "compliance_point_id", nullable = false)
    private CompliancePoint compliancePoint;

    private String note;

    @ManyToOne
    private User createdBy;

    private LocalDateTime mappedAt;
}
