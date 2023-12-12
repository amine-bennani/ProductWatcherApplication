package com.imedia24.productWatcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.imedia24.productWatcher.model.Price;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {
    List<Price> findBySkuOrderByDateDesc(int sku);
}