package tes.project.inventory.domain.product.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tes.project.inventory.domain.product.Product;
import tes.project.inventory.domain.product.dto.ProductDTO;
import tes.project.inventory.domain.product.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    private ProductRepository repository;

    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO data) {
        Product product = new Product(data.description(), data.category(), data.quantity(), data.price(), data.notes());

        this.repository.save(product);

        return ResponseEntity.status(201).build();
    }

    @PostMapping("/batch")
    public ResponseEntity<Void> createBatch(@Valid @RequestBody List<ProductDTO> products) {
        List<Product> productEntities = products.stream()
                .map(data -> new Product(
                        data.description(),
                        data.category(),
                        data.quantity(),
                        data.price(),
                        data.notes()
                ))
                .collect(Collectors.toList());

        repository.saveAll(productEntities);

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO data) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found!"));

        product.setDescription(data.description());
        product.setCategory(data.category());
        product.setQuantity(data.quantity());
        product.setPrice(data.price());
        product.setNotes(data.notes());

        repository.save(product);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> index() {
        List<Product> products = repository.findAll();

        List<ProductDTO> productDTOs = products.stream()
                .map(product -> new ProductDTO(
                        product.getDescription(),
                        product.getCategory(),
                        product.getQuantity(),
                        product.getPrice(),
                        product.getNotes()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(productDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> find(@PathVariable Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found!"));

        return ResponseEntity.ok(
            new ProductDTO(
                product.getDescription(),
                product.getCategory(),
                product.getQuantity(),
                product.getPrice(),
                product.getNotes()
        ));
    }
}
