package com.imedia24.productWatcher.service;

import java.util.List;
import com.imedia24.productWatcher.repository.PriceRepository;
import com.imedia24.productWatcher.model.Price;

public class PriceService {
	
	PriceRepository priceRepository;

	public List<Price> paginatePricesByProduct(int sku, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public Price createPrice(Price price) {
		return priceRepository.save(price);
	}

}
