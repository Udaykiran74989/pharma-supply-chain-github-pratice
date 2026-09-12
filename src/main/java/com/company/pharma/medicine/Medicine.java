package com.company.pharma.medicine;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="medicines")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Medicine {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String code;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String unit;
}
