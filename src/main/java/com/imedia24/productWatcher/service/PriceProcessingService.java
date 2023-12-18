import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.imedia24.productWatcher.model.Action;
import com.imedia24.productWatcher.model.Price;
import com.imedia24.productWatcher.repository.PriceRepository;

@Service
public class PriceProcessingService {

    private final PriceRepository priceRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public PriceProcessingService(PriceRepository priceRepository, KafkaTemplate<String, Object> kafkaTemplate) {
        this.priceRepository = priceRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void processPricesAndNotify() {
        // Retrieve all prices from the database
        List<Price> allPrices = priceRepository.findAll();

        // Find four best dates to buy (lower prices)
        List<LocalDate> bestDatesToBuy = allPrices.stream()
                .sorted(Comparator.comparing(Price::getPrice))
                .limit(4)
                .map(Price::getDate)
                .collect(Collectors.toList());

        // Find four best dates to sell (higher prices)
        List<LocalDate> bestDatesToSell = allPrices.stream()
                .sorted(Comparator.comparing(Price::getPrice).reversed())
                .limit(4)
                .map(Price::getDate)
                .collect(Collectors.toList());

        // Notify and push results to Kafka topic
        notifyAndPushToKafka("BUY", bestDatesToBuy);
        notifyAndPushToKafka("SELL", bestDatesToSell);
    }

    private void notifyAndPushToKafka(String actionType, List<LocalDate> dates) {
        // Notify or perform further actions with the results
        System.out.println("Best dates to " + actionType.toLowerCase() + ": " + dates);

        // Push results to Kafka topic
        dates.forEach(date -> {
            Action action = new Action("1", actionType, date);
            kafkaTemplate.send("your_kafka_topic", action);
        });
    }
}
