package com.imedia24.productWatcher.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imedia24.productWatcher.cron.PriceProcessingJob;
import com.imedia24.productWatcher.model.ProcessPricesResponse;
import com.imedia24.productWatcher.service.PriceProcessingService;

import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired(required=true)
    private PriceProcessingJob myCronJob;


    @GetMapping("/triggerCronJobTest")
    public ProcessPricesResponse triggerCronJobTest() {
        // Call the testCronJob method of CronJobTester
    	return myCronJob.triggerCronJobManually();
    }
}
