package com.company.pharma.order;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="distributor_orders")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DistributorOrder {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String orderNumber;

    @Column(nullable=false)
    private String distributorCode;

    @Column(nullable=false)
    private String batchNumber;

    @Column(nullable=false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private OrderStatus status;
}
