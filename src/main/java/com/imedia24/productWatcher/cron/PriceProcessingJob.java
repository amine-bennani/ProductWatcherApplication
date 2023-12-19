package com.imedia24.productWatcher.cron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.imedia24.productWatcher.model.ProcessPricesResponse;
import com.imedia24.productWatcher.service.PriceProcessingService;

@Component
public class PriceProcessingJob {
	
	@Autowired
    private final PriceProcessingService priceProcessingService;
    
    @Autowired
    private ApplicationContext context;
    
    public PriceProcessingJob(PriceProcessingService priceProcessingService) {
        this.priceProcessingService = priceProcessingService;
    }
    
    public ProcessPricesResponse triggerCronJobManually() {
        // Manually invoke the cron job
        return context.getBean(PriceProcessingJob.class).processPricesAndNotify();
    }

    // Scheduled job to run every Friday at 00:00 GMT
    @Scheduled(cron = "0 0 0 ? * FRI", zone = "GMT")
    public ProcessPricesResponse processPricesAndNotify() {
        return priceProcessingService.processPricesAndNotify();
    }
}