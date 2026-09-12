package com.company.pharma.batch;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MedicineBatchRepository extends JpaRepository<MedicineBatch, Long> {
    Optional<MedicineBatch> findByBatchNumber(String batchNumber);
}
