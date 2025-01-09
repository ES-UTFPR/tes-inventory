package tes.project.inventory.domain.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductDTO(
    @NotNull(message = "Product description cannot be null")
    @Size(min = 3, max = 100, message = "Description should have between 3 and 100 characters")
    String description,

    @NotNull(message = "Category cannot be null")
    @Size(min = 3, max = 50, message = "Category should have between 3 and 100 characters")
    String category,

    @NotNull(message = "Quantity cannot be null")
    int quantity,

    @NotNull(message = "Price cannot be null")
    double price,

    @Size(max = 500, message = "Notes can have a maximum of 500 characters")
    String notes
) {
}
