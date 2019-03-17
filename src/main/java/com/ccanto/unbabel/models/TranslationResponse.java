package com.ccanto.unbabel.models;

public class TranslationResponse {
	private long order_number;
	private double price;
	private String source_language;
	private String status;
	private String target_language;
	private String text;
	private String text_format;
	private String uid;

	public long getOrder_number() {
		return order_number;
	}

	public void setOrder_number(long order_number) {
		this.order_number = order_number;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getSource_language() {
		return source_language;
	}

	public void setSource_language(String source_language) {
		this.source_language = source_language;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTarget_language() {
		return target_language;
	}

	public void setTarget_language(String target_language) {
		this.target_language = target_language;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText_format() {
		return text_format;
	}

	public void setText_format(String text_format) {
		this.text_format = text_format;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "TranslationResponse{" + "order_number=" + order_number + ", price=" + price + ", source_language='" + source_language + '\'' + ", status='" + status + '\'' + ", target_language='" + target_language + '\'' + ", text='" + text + '\'' + ", text_format='" + text_format + '\'' + ", uid='" + uid + '\'' + '}';
	}
}
