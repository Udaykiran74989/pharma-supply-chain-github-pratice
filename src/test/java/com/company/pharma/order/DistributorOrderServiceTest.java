package com.company.pharma.order;

import com.company.pharma.batch.*;
import com.company.pharma.medicine.Medicine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class DistributorOrderServiceTest {

    @Mock DistributorOrderRepository orderRepository;
    @Mock MedicineBatchRepository batchRepository;

    @InjectMocks DistributorOrderService service;

    @Test
    void shouldCreateOrderWhenStockIsAvailable() {
        Medicine medicine = Medicine.builder().id(1L).code("MED-1").name("Test Medicine").unit("BOX").build();
        MedicineBatch batch = MedicineBatch.builder()
            .id(1L).batchNumber("B-001").medicine(medicine)
            .expiryDate(LocalDate.now().plusMonths(6))
            .availableQuantity(100)
            .qualityStatus(QualityStatus.APPROVED)
            .build();

        when(batchRepository.findByBatchNumber("B-001")).thenReturn(Optional.of(batch));
        when(orderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        DistributorOrder result = service.create(new CreateOrderRequest("DIST-01", "B-001", 20));

        assertEquals("DIST-01", result.getDistributorCode());
        assertEquals(80, batch.getAvailableQuantity());
        verify(orderRepository).save(any());
    }
    @Test
    void shouldRejectWhenBatchIsPending() {
        MedicineBatch batch = MedicineBatch.builder()
                .batchNumber("B-003")
                .availableQuantity(50)
                .qualityStatus(QualityStatus.PENDING)
                .build();

        when(batchRepository.findByBatchNumber("B-003"))
                .thenReturn(Optional.of(batch));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.create(
                        new CreateOrderRequest("DIST-01", "B-003", 10)
                )
        );

        assertEquals(
                "Batch is not approved for ordering",
                exception.getMessage()
        );

        assertEquals(50, batch.getAvailableQuantity());
        verify(orderRepository, never()).save(any());
        verify(batchRepository, never()).save(any());
    }

    @Test
    void shouldRejectWhenBatchIsRejected() {
        MedicineBatch batch = MedicineBatch.builder()
                .batchNumber("B-004")
                .availableQuantity(50)
                .qualityStatus(QualityStatus.REJECTED)
                .build();

        when(batchRepository.findByBatchNumber("B-004"))
                .thenReturn(Optional.of(batch));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.create(
                        new CreateOrderRequest("DIST-01", "B-004", 10)
                )
        );

        assertEquals(
                "Batch is not approved for ordering",
                exception.getMessage()
        );

        assertEquals(50, batch.getAvailableQuantity());
        verify(orderRepository, never()).save(any());
        verify(batchRepository, never()).save(any());
    }

    @Test
    void shouldRejectWhenStockIsInsufficient() {
        MedicineBatch batch = MedicineBatch.builder()
            .batchNumber("B-002").availableQuantity(10)
            .qualityStatus(QualityStatus.APPROVED)
            .build();

        when(batchRepository.findByBatchNumber("B-002")).thenReturn(Optional.of(batch));

        assertThrows(IllegalArgumentException.class,
            () -> service.create(new CreateOrderRequest("DIST-01", "B-002", 11)));

        verify(orderRepository, never()).save(any());
    }
}
