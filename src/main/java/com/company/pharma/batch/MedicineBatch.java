package com.company.pharma.batch;

import com.company.pharma.medicine.Medicine;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="medicine_batches")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MedicineBatch {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String batchNumber;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    private Medicine medicine;

    @Column(nullable=false)
    private LocalDate expiryDate;

    @Column(nullable=false)
    private Integer availableQuantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private QualityStatus qualityStatus;
}
