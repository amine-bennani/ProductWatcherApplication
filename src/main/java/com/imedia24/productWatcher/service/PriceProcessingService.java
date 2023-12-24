package com.imedia24.productWatcher.service;

import com.imedia24.productWatcher.model.Action;
import com.imedia24.productWatcher.model.Price;
import com.imedia24.productWatcher.model.ProcessPricesResponse;
import com.imedia24.productWatcher.repository.PriceRepository;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


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
        List<Price> allPrices = priceRepository.findAllByOrderByPriceAsc();
        List<Action> listActions = new ArrayList<Action>();
        int actionNumbers = (allPrices.size() / 2) - 1;
        for (int i=0;i<actionNumbers;i++) {
        	
        	Price lowestPrice = allPrices.get(0);
        	Action buyAction = new Action (lowestPrice.getSku(),"BUY",lowestPrice.getDate().toString());
        	listActions.add(buyAction);
        	//kafkaTemplate.send("your_kafka_topic", buyAction);
        	
        	Price highestPrice = allPrices.get(allPrices.size()-1);
        	Action sellAction = new Action (highestPrice.getSku(),"SELL",highestPrice.getDate().toString());
        	listActions.add(sellAction);
        	//kafkaTemplate.send("your_kafka_topic", sellAction);
        	
        	allPrices.remove(0);
        	allPrices.remove(allPrices.size()-1);
        }        
        return new ProcessPricesResponse(listActions);
    }

//    private void  notifyAndPushToKafka(String actionType, List<LocalDate> dates) {
//        // Notify or perform further actions with the results
//        System.out.println("Best dates to " + actionType.toLowerCase() + ": " + dates);
//
//        // Push results to Kafka topic
//        dates.forEach(date -> {
//            Action action = new Action("1", actionType, date.toString());
//            
//        });
//        
        
        
    
}
