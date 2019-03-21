package com.ccanto.unbabel.dataacess;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TranslationResponse{
	@Id
	private String uid;
	@Column
	private long order_number;
	@Column
	private double price;
	@Column(nullable = false)
	private String source_language;
	@Column
	private String status;
	@Column(nullable = false)
	private String target_language;
	@Column(nullable = false)
	private String text;
	@Column
	private String text_format;
	@Column
	private String translatedText;
	@Column
	private String create_date;
	@Column
	private String update_date;

	public String getUid() {
		return uid;
	}

	public TranslationResponse setUid(String uid) {
		this.uid = uid;
		return this;
	}

	public long getOrder_number() {
		return order_number;
	}

	public TranslationResponse setOrder_number(long order_number) {
		this.order_number = order_number;
		return this;
	}

	public double getPrice() {
		return price;
	}

	public TranslationResponse setPrice(double price) {
		this.price = price;
		return this;
	}

	public String getSource_language() {
		return source_language;
	}

	public TranslationResponse setSource_language(String source_language) {
		this.source_language = source_language;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public TranslationResponse setStatus(String status) {
		this.status = status;
		return this;
	}

	public String getTarget_language() {
		return target_language;
	}

	public TranslationResponse setTarget_language(String target_language) {
		this.target_language = target_language;
		return this;
	}

	public String getText() {
		return text;
	}

	public TranslationResponse setText(String text) {
		this.text = text;
		return this;
	}

	public String getText_format() {
		return text_format;
	}

	public TranslationResponse setText_format(String text_format) {
		this.text_format = text_format;
		return this;
	}

	public String getTranslatedText() {
		return translatedText;
	}

	public TranslationResponse setTranslatedText(String translatedText) {
		this.translatedText = translatedText;
		return this;
	}

	public String getCreate_date() {
		return create_date;
	}

	public TranslationResponse setCreate_date(String create_date) {
		this.create_date = create_date;
		return this;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public TranslationResponse setUpdate_date(String update_date) {
		this.update_date = update_date;
		return this;
	}

	@Override
	public String toString() {
		return "TranslationResponse{" + "uid='" + uid + '\'' + ", order_number=" + order_number + ", price=" + price + ", source_language='" + source_language + '\'' + ", status='" + status + '\'' + ", target_language='" + target_language + '\'' + ", text='" + text + '\'' + ", text_format='" + text_format + '\'' + ", translatedText='" + translatedText + '\'' + ", create_date='" + create_date + '\'' + ", update_date='" + update_date + '\'' + '}';
	}
}
