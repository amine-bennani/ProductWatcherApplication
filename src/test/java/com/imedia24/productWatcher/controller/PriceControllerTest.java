package com.imedia24.productWatcher.controller;

import com.imedia24.productWatcher.controller.*;
import com.imedia24.productWatcher.model.Price;
import com.imedia24.productWatcher.service.PriceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ValidationException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    @Test
    void testCreatePriceSuccessfully() {
        Price mockPrice = new Price();
        when(priceService.createPrice(any(Price.class))).thenReturn(mockPrice);

        ResponseEntity<Price> responseEntity = priceController.createPrice(new Price());

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(mockPrice, responseEntity.getBody());
    }

    @Test
    void testCreatePriceWithValidationFailure() {
        when(priceService.createPrice(any(Price.class))).thenThrow(ValidationException.class);

        ResponseEntity<Price> responseEntity = priceController.createPrice(new Price());

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testPaginatePricesByProduct() {
        List<Price> mockPrices = Arrays.asList(new Price(), new Price());
        when(priceService.paginatePricesByProduct(anyInt(), anyInt(), anyInt())).thenReturn(mockPrices);

        ResponseEntity<List<Price>> responseEntity = priceController.paginatePricesByProduct(123, 0, 10);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockPrices, responseEntity.getBody());
    }
}

