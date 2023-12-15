package com.imedia24.productWatcher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import com.imedia24.productWatcher.model.Price;
import com.imedia24.productWatcher.service.PriceService;

@RestController
@RequestMapping("/prices")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @PostMapping(value = "/price", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Price> createPrice(@Valid @RequestBody Price price) {
        Price createdPrice = priceService.createPrice(price);
        return new ResponseEntity<>(createdPrice, HttpStatus.CREATED);
    }

    @GetMapping("/paginate/{sku}")
    public ResponseEntity<List<Price>> paginatePricesByProduct(
    		@PathVariable @Min(1) int sku,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<Price> paginatedPrices = priceService.paginatePricesByProduct(sku, page, size);
        return new ResponseEntity<>(paginatedPrices, HttpStatus.OK);
    }
}
