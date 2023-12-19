package com.imedia24.productWatcher.test.controller;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceProcessingJobTest {

    @Autowired
    private PriceProcessingJob myCronJob;

    public void testCronJob() {
        // Trigger the cron job manually for testing
        myCronJob.triggerCronJobManually();
    }
}
