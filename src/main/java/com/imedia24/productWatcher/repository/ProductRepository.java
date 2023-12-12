package com.imedia24.productWatcher.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.imedia24.productWatcher.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Additional custom queries can be added here if needed
}
