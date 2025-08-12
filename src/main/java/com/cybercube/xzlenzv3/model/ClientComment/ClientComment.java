package com.cybercube.xzlenzv3.model.ClientComment;

import java.time.LocalDateTime;

import com.cybercube.xzlenzv3.model.ClientCCFPoint.ClientCCFPoint;
import com.cybercube.xzlenzv3.model.ClientCompliance.ClientCompliancePoint;
import com.cybercube.xzlenzv3.model.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "client_comments")
@Data
public class ClientComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String comment;

    @ManyToOne
    private User createdBy;

    private LocalDateTime createdAt;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "client_ccf_point_id")
    private ClientCCFPoint clientCCFPoint;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "client_compliance_point_id")
    private ClientCompliancePoint clientCompliancePoint;
    


}
