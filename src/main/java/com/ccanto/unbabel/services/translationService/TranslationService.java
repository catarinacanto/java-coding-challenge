package com.ccanto.unbabel.services.translationService;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class TranslationService {


	public void execute(String text, String targetLanguage) {
		HttpsURLConnection conn;
		OutputStream outputStream = null;
		BufferedWriter writer = null;
		JSONObject request;
		try {
			conn = getSandBoxConnection();
			outputStream = conn.getOutputStream();
			writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
			request = readRequest(text, targetLanguage);
			writer.write(request.toString());
			System.out.println(conn.getInputStream());
			conn.connect();

		} catch (IOException e) {
			e.getMessage();
		} finally {
			try {
				if (writer != null) {
					writer.flush();
					writer.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (IOException e) {
				e.getMessage();
			}
		}

	}

	private HttpsURLConnection getSandBoxConnection() throws IOException {
		URL url = new URL("https://sandbox.unbabel.com/tapi/v2/translation/");
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setReadTimeout(10000);
		conn.setConnectTimeout(15000);
		conn.setRequestMethod("POST");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Authorization", "ApiKey fullstack-challenge:9db71b322d43a6ac0f681784ebdcc6409bb83359");
		conn.setRequestProperty("Content-Type", "application/json");
		return conn;
	}

	private JSONObject readRequest(String text, String targetLanguage) {
		JSONObject request = new JSONObject();

		try {
			request.put("text", text);
			request.put("source_language", "en");
			request.put("target_language", targetLanguage);

		} catch (JSONException e) {
			e.getMessage();
		}
		return request;
	}


}
