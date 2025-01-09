package tes.project.inventory.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tes.project.inventory.domain.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
