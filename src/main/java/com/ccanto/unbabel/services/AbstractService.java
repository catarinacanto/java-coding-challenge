package com.ccanto.unbabel.services;

import com.ccanto.unbabel.Authorization;
import com.sun.net.httpserver.Headers;

public abstract class AbstractService {

	protected void initialize(){
		Headers headers = new Headers();
		headers.add("Authorization", Authorization.getInstance().toString());
	}
}
