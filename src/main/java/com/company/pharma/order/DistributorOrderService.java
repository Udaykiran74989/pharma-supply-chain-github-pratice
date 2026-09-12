package com.company.pharma.order;
import com.company.pharma.batch.QualityStatus;

import com.company.pharma.batch.MedicineBatch;
import com.company.pharma.batch.MedicineBatchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DistributorOrderService {
    private final DistributorOrderRepository orderRepository;
    private final MedicineBatchRepository batchRepository;

    public DistributorOrderService(DistributorOrderRepository orderRepository,
                                   MedicineBatchRepository batchRepository) {
        this.orderRepository = orderRepository;
        this.batchRepository = batchRepository;
    }

    @Transactional
    public DistributorOrder create(CreateOrderRequest request) {
        MedicineBatch batch = batchRepository.findByBatchNumber(request.batchNumber())
            .orElseThrow(() -> new IllegalArgumentException("Batch not found: " + request.batchNumber()));

        if (batch.getQualityStatus() != QualityStatus.APPROVED) {
            throw new IllegalArgumentException("Batch is not approved for ordering");
        }

        if (request.quantity() > batch.getAvailableQuantity()) {
            throw new IllegalArgumentException("Insufficient batch stock");
        }

        batch.setAvailableQuantity(batch.getAvailableQuantity() - request.quantity());
        batchRepository.save(batch);

        DistributorOrder order = DistributorOrder.builder()
            .orderNumber("DO-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase())
            .distributorCode(request.distributorCode())
            .batchNumber(request.batchNumber())
            .quantity(request.quantity())
            .status(OrderStatus.CREATED)
            .build();

        return orderRepository.save(order);
    }
}
