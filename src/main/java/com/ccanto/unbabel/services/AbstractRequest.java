package com.ccanto.unbabel.services;

import com.sun.net.httpserver.Headers;

public abstract class AbstractRequest {
	protected final String url = "https://sandbox.unbabel.com/tapi/v2/translation";
	protected final String rawUrl = "https://sandbox.unbabel.com/tapi/v2/translation/";
	protected String method;
	protected Headers headers;


	public String getUrl() {
		return url;
	}

	public String getRawUrl() {
		return rawUrl;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Headers getHeaders() {
		return headers;
	}

	public void setHeaders(Headers headers) {
		this.headers = headers;
	}

	@Override
	public String toString() {
		return "AbstractRequest{" + "url='" + url + '\'' + ", rawUrl='" + rawUrl + '\'' + ", method='" + method + '\'' + ", headers=" + headers + '}';
	}
}







