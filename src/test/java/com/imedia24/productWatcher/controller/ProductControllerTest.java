package com.imedia24.productWatcher.controller;

import com.imedia24.productWatcher.controller.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    void testCreateProductSuccessfully() {
        // Mocking the ProductService to return a created product
        Product mockProduct = new Product(1, "TestProduct");
        when(productService.createProduct(any(Product.class))).thenReturn(mockProduct);

        // Testing the ProductController's createProduct method
        ResponseEntity<Product> responseEntity = productController.createProduct(new Product(1, "TestProduct"));

        // Asserting the response
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(mockProduct, responseEntity.getBody());
    }

    @Test
    void testCreateProductWithValidationFailure() {
        // Mocking the ProductService to throw a ValidationException
        when(productService.createProduct(any(Product.class))).thenThrow(ValidationException.class);

        // Testing the ProductController's createProduct method with a validation failure
        ResponseEntity<Product> responseEntity = productController.createProduct(new Product());

        // Asserting the response
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}
