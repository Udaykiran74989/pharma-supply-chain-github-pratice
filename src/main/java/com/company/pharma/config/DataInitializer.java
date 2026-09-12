package com.company.pharma.config;

import com.company.pharma.batch.*;
import com.company.pharma.medicine.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner seed(MedicineRepository medicines, MedicineBatchRepository batches) {
        return args -> {
            Medicine medicine = medicines.findAll().stream().findFirst().orElseGet(() ->
                medicines.save(Medicine.builder()
                    .code("MED-1001").name("Paracetamol 500mg").unit("TABLET").build()));

            if (batches.count() == 0) {
                batches.save(MedicineBatch.builder()
                    .batchNumber("BATCH-PARA-001")
                    .medicine(medicine)
                    .expiryDate(LocalDate.now().plusMonths(8))
                    .availableQuantity(1000)
                    .qualityStatus(QualityStatus.PENDING)
                    .build());

                batches.save(MedicineBatch.builder()
                    .batchNumber("BATCH-PARA-002")
                    .medicine(medicine)
                    .expiryDate(LocalDate.now().plusMonths(14))
                    .availableQuantity(500)
                    .qualityStatus(QualityStatus.APPROVED)
                    .build());
            }
        };
    }
}
