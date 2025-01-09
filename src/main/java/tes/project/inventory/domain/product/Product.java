package tes.project.inventory.domain.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "products")
@Entity(name = "products")
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String category;
    private int quantity;
    private double price;
    private String notes;

    public Product(String description, String category, int quantity, double price, String notes) {
        this.description = description;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.notes = notes;
    }
}
