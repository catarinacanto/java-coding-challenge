package com.ccanto.unbabel.services.translationService;

public class TranslationResponse {
	private long orderNumber;
	private double price;
	private String sourceLanguage;
	private String status;
	private String targetLanguage;
	private String text;
	private String textFormat;
	private String uid;

	public long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getSourceLanguage() {
		return sourceLanguage;
	}

	public void setSourceLanguage(String sourceLanguage) {
		this.sourceLanguage = sourceLanguage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTargetLanguage() {
		return targetLanguage;
	}

	public void setTargetLanguage(String targetLanguage) {
		this.targetLanguage = targetLanguage;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTextFormat() {
		return textFormat;
	}

	public void setTextFormat(String textFormat) {
		this.textFormat = textFormat;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "TranslationResponse{" + "orderNumber=" + orderNumber + ", price=" + price + ", sourceLanguage='" + sourceLanguage + '\'' + ", status='" + status + '\'' + ", targetLanguage='" + targetLanguage + '\'' + ", text='" + text + '\'' + ", textFormat='" + textFormat + '\'' + ", uid='" + uid + '\'' + '}';
	}
}
