package com.imedia24.productWatcher.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.imedia24.productWatcher.model.Action;
import com.imedia24.productWatcher.model.Price;
import com.imedia24.productWatcher.model.ProcessPricesResponse;
import com.imedia24.productWatcher.repository.PriceRepository;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.ArrayList;

import java.util.PriorityQueue;


@Service
public class PriceProcessingService {

    private final PriceRepository priceRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
	static final int NUM_DATES_TO_FIND = 4;
	 private static ApiResponse cronJobResponse;

    @Autowired
    public PriceProcessingService(PriceRepository priceRepository, KafkaTemplate<String, Object> kafkaTemplate) {
        this.priceRepository = priceRepository;
        this.kafkaTemplate = kafkaTemplate;
    }
    
    public static void setCronJobResponse(ApiResponse response) {
        cronJobResponse = response;
    }

    public ProcessPricesResponse getCronJobResponse() {
    	return processPricesAndNotify();
    }

    public ProcessPricesResponse processPricesAndNotify() {
        // Retrieve all prices from the database
        List<Price> allPrices = priceRepository.findAll();

        // Find best dates to buy and sell
        List<LocalDate> bestDatesToBuy = findBestDates(allPrices, NUM_DATES_TO_FIND, Comparator.comparing(Price::getPrice));
        List<LocalDate> bestDatesToSell = findBestDates(allPrices, NUM_DATES_TO_FIND, Comparator.comparing(Price::getPrice).reversed());
        
        List<Action> actionList= new ArrayList<Action>();
        bestDatesToBuy.forEach(date -> {
            Action action = new Action("1", "BUY", date.toString());
            actionList.add(action);
            //kafkaTemplate.send("your_kafka_topic", action);
        });
        bestDatesToSell.forEach(date -> {
            Action action = new Action("1", "SELL", date.toString());
            actionList.add(action);
            //kafkaTemplate.send("your_kafka_topic", action);
        });
        // Notify and push results to Kafka topic
//        notifyAndPushToKafka("BUY", bestDatesToBuy);
//        notifyAndPushToKafka("SELL", bestDatesToSell);
        
        return new ProcessPricesResponse(actionList);
    }

    private List<LocalDate> findBestDates(List<Price> prices, int numDates, Comparator<Price> comparator) {
        PriorityQueue<Price> priceQueue = new PriorityQueue<>(comparator);

        for (Price price : prices) {
            priceQueue.offer(price);
            if (priceQueue.size() > numDates) {
                priceQueue.poll(); // Remove the lowest or highest price depending on the comparator
            }
        }

        List<LocalDate> result = new ArrayList<>();
        while (!priceQueue.isEmpty()) {
            result.add(priceQueue.poll().getDate());
        }

        return result;
    }

    private void  notifyAndPushToKafka(String actionType, List<LocalDate> dates) {
        // Notify or perform further actions with the results
        System.out.println("Best dates to " + actionType.toLowerCase() + ": " + dates);

        // Push results to Kafka topic
        dates.forEach(date -> {
            Action action = new Action("1", actionType, date.toString());
            //kafkaTemplate.send("your_kafka_topic", action);
        });
        
        
        
    }
}
