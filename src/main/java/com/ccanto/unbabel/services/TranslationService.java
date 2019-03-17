package com.ccanto.unbabel.services;

import com.ccanto.unbabel.models.TranslationResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

@Service
public class TranslationService {

	public TranslationResponse execute(String text, String sourceLanguage, String targetLanguage) throws IOException {
		URL url = new URL("https://sandbox.unbabel.com/tapi/v2/translation/");
		HttpsURLConnection conn = getSandBoxConnection();
		JSONObject request = formRequest(text, sourceLanguage, targetLanguage);

		return getTranslation(conn, request, url);
	}

	private HttpsURLConnection getSandBoxConnection() throws IOException {
		URL url = new URL("https://sandbox.unbabel.com/tapi/v2/translation/");
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setReadTimeout(100000);
		conn.setConnectTimeout(15000);
		conn.setRequestMethod("POST");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Authorization", "ApiKey fullstack-challenge:9db71b322d43a6ac0f681784ebdcc6409bb83359");
		conn.setRequestProperty("Content-Type", "application/json");
		return conn;
	}

	public JSONObject formRequest(String text, String sourceLanguage, String targetLanguage) {
		JSONObject request = new JSONObject();

		try {
			request.put("text", text);
			request.put("source_language", sourceLanguage);
			request.put("target_language", targetLanguage);

		} catch (JSONException e) {
			e.getMessage();
		}
		return request;
	}


	private TranslationResponse getTranslation(HttpsURLConnection conn, JSONObject request, URL url) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		OutputStream os = conn.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
		writer.write(request.toString());
		writer.flush();
		writer.close();
		os.close();

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuilder jsonResponse = new StringBuilder();

		while ((inputLine = in.readLine()) != null) {
			jsonResponse.append(inputLine);
		}
		in.close();
		System.out.println(jsonResponse.toString());
		return objectMapper.readValue(jsonResponse.toString(), TranslationResponse.class);
	}

}
