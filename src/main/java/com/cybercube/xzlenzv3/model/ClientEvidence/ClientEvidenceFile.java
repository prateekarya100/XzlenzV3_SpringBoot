package com.cybercube.xzlenzv3.model.ClientEvidence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "client_evidence_files")
@Data
public class ClientEvidenceFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String fileName;
    
    private String subject;

    @JsonIgnore
    @Lob
    private byte[] fileData;
    
    @JsonIgnore
    private String filePath; // Path of stored file in directory

    private String fileType;
    
    
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

    
    
 // Populate fileData from stored file when fetched
    public byte[] getFileData() {
        if (this.filePath != null) {
            try {
                Path path = Paths.get(this.filePath);

                if (!Files.exists(path)) {
                    System.err.println("File not found at: " + this.filePath);
                    return null;
                }

                System.out.println("Reading file from path: " + this.filePath);
                byte[] f = Files.readAllBytes(path);
                return f;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Error: filePath is null");
        }
        return null;
    }

    
    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }




}
