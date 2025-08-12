package com.cybercube.xzlenzv3.model.DataPrivacyPrinciple;

import java.time.LocalDateTime;

import com.cybercube.xzlenzv3.model.CCFPoints.CCFPoint;
import com.cybercube.xzlenzv3.model.User.User;

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
@Table(name = "ccf_privacy_mapping",
       uniqueConstraints = @UniqueConstraint(columnNames = {"ccf_point_id", "data_privacy_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CCFPrivacyMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ccf_point_id", nullable = false)
    private CCFPoint ccfPoint;

    @ManyToOne
    @JoinColumn(name = "data_privacy_id", nullable = false)
    private DataPrivacyPrinciple dataPrivacyPrinciple;

    private String note;
    @ManyToOne
    private User createdBy;

    private LocalDateTime mappedAt;
}
