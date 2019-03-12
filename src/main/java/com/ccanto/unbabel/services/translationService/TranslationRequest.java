package com.ccanto.unbabel.services.translationService;

import com.ccanto.unbabel.services.AbstractRequest;

public class TranslationRequest extends AbstractRequest {

	private String text;
	private String sourceLanguage;
	private String targetLanguage;
	private String textFormat;

	public String getText() {
		return text;
	}

	public TranslationRequest setText(String text) {
		this.text = text;
		return this;
	}

	public String getSourceLanguage() {
		return sourceLanguage;
	}

	public TranslationRequest setSourceLanguage(String sourceLanguage) {
		this.sourceLanguage = sourceLanguage;
		return this;
	}

	public String getTargetLanguage() {
		return targetLanguage;
	}

	public TranslationRequest setTargetLanguage(String targetLanguage) {
		this.targetLanguage = targetLanguage;
		return this;
	}

	public String getTextFormat() {
		return textFormat;
	}

	public TranslationRequest setTextFormat(String textFormat) {
		this.textFormat = textFormat;
		return this;
	}

	@Override
	public String toString() {
		return super.toString() + "TranslationRequest{" + "text='" + text + '\'' + ", sourceLanguage='" + sourceLanguage + '\'' + ", targetLanguage='" + targetLanguage + '\'' + ", textFormat='" + textFormat + '\'' + '}';
	}
}
