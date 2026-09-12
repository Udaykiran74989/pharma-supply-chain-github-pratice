package com.company.pharma.order;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/distributor-orders")
public class DistributorOrderController {
    private final DistributorOrderService service;

    public DistributorOrderController(DistributorOrderService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DistributorOrder create(@Valid @RequestBody CreateOrderRequest request) {
        return service.create(request);
    }
}
