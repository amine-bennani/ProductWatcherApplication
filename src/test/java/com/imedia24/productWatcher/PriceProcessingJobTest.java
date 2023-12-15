package com.imedia24.productWatcher;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.util.ReflectionTestUtils;

import com.imedia24.productWatcher.cron.PriceProcessingJob;
import com.imedia24.productWatcher.service.PriceProcessingService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class PriceProcessingJobTest {

    @Mock
    private PriceProcessingService priceProcessingService;

    @InjectMocks
    private PriceProcessingJob priceProcessingJob;

    @Test
    void testProcessPricesAndNotify() {
        // Mock the PriceProcessingService
        PriceProcessingService priceProcessingService = Mockito.mock(PriceProcessingService.class);

        // Create an instance of the PriceProcessingJob
        PriceProcessingJob priceProcessingJob = new PriceProcessingJob(priceProcessingService);

        // Use ReflectionTestUtils to set the cron expression for testing
        ReflectionTestUtils.setField(priceProcessingJob, "cronExpression", "0 0 0 ? * FRI");

        // Execute the scheduled task
        priceProcessingJob.processPricesAndNotify();

        // Verify that the processPricesAndNotify method is called
        verify(priceProcessingService, times(1)).processPricesAndNotify();
    }
}
