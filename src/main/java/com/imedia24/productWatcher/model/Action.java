package com.imedia24.productWatcher.model;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Action {

    private String sku;
    private String type;

    @JsonFormat(pattern = "dd-MM-yyyy") // Specify the date format
    private String date;

    public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Action(String sku, String type, String date) {
        this.sku = sku;
        this.type = type;
        this.date = date;
    }

    // getters and setters
}

