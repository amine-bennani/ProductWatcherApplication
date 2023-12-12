package com.imedia24.productWatcher.cron;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.imedia24.productWatcher.service.PriceProcessingService;

@Component
public class PriceProcessingJob {

    private final PriceProcessingService priceProcessingService;

    public PriceProcessingJob(PriceProcessingService priceProcessingService) {
        this.priceProcessingService = priceProcessingService;
    }

    // Scheduled job to run every Friday at 00:00 GMT
    @Scheduled(cron = "0 0 0 ? * FRI", zone = "GMT")
    public void processPricesAndNotify() {
        priceProcessingService.processPricesAndNotify();
    }
}
