    package com.company.pharma.order;

    import jakarta.validation.constraints.Min;
    import jakarta.validation.constraints.NotBlank;

    public record CreateOrderRequest(
        @NotBlank String distributorCode,
        @NotBlank String batchNumber,
        @Min(1) Integer quantity
    ) {}
