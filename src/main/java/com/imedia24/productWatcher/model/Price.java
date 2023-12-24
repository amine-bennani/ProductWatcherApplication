package com.imedia24.productWatcher.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "price")
public class Price implements Comparable<Price>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sku;
    private LocalDate date;
    private Double price;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	 @Override
    public int compareTo(Price other) {
        // Implement comparison logic based on the double value
        // Return a negative value if this instance is less than the other
        // Return 0 if this instance is equal to the other
        // Return a positive value if this instance is greater than the other
        return Double.compare(this.price, other.price);
    }
    // Getters and setters
}