package com.ccanto.unbabel.models;

public class Translation {
	private String uid;
	private String from;
	private String original;
	private String to;
	private String translated;
	private String status;


	public String getUid() {
		return uid;
	}

	public Translation setUid(String uid) {
		this.uid = uid;
		return this;
	}

	public String getFrom() {
		return from;
	}

	public Translation setFrom(String from) {
		this.from = from;
		return this;
	}

	public String getOriginal() {
		return original;
	}

	public Translation setOriginal(String original) {
		this.original = original;
		return this;
	}

	public String getTo() {
		return to;
	}

	public Translation setTo(String to) {
		this.to = to;
		return this;
	}

	public String getTranslated() {
		return translated;
	}

	public Translation setTranslated(String translated) {
		this.translated = translated;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public Translation setStatus(String status) {
		this.status = status;
		return this;
	}

	@Override
	public String toString() {
		return "Translation{" + "uid='" + uid + '\'' + ", from='" + from + '\'' + ", original='" + original + '\'' + ", to='" + to + '\'' + ", translated='" + translated + '\'' + ", status='" + status + '\'' + '}';
	}
}
