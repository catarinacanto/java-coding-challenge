package com.ccanto.unbabel.services.translation;

import com.ccanto.unbabel.constants.ConstantsEnum;
import com.ccanto.unbabel.dataacess.TranslationResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

@Service
public class TranslationService {

	private Logger log = LogManager.getLogger(TranslationService.class);


	/**
	 * Executed when a new request is made, receives
	 * @param text
	 * @param sourceLanguage
	 * @param targetLanguage
	 * @return the new request object
	 * @throws IOException
	 */
	public TranslationResponse execute(String text, String sourceLanguage, String targetLanguage) throws IOException {
		URL url = new URL(ConstantsEnum.SANDBOX_URL.getValue());
		HttpsURLConnection conn = getSandBoxConnection(url,HttpMethod.POST.name());
		JSONObject request = formRequest(text, sourceLanguage, targetLanguage);
		return getTranslation(conn, request);
	}

	/**
	 * Executed when an update request is made, receives
	 * @param uid
	 * @return the new updated object
	 * @throws IOException
	 */
	public TranslationResponse execute(String uid) throws IOException {
		URL url = new URL(ConstantsEnum.SANDBOX_URL.getValue() + uid + "/");
		HttpsURLConnection conn = getSandBoxConnection(url, HttpMethod.GET.name());
		return getTranslatedText(conn);
	}

	/**
	 * Receives
	 * @param url
	 * @param method
	 * sets the connection and required headers
	 * @return the connection to unbabel sandbox API
	 * @throws IOException
	 */
	private HttpsURLConnection getSandBoxConnection(URL url,String method) throws IOException {
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setReadTimeout(100000);
		conn.setConnectTimeout(15000);
		conn.setRequestMethod(method);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty(ConstantsEnum.AUTHORIZATION.getValue(), ConstantsEnum.API_KEY.getValue());
		conn.setRequestProperty(ConstantsEnum.CONTENT_TYPE.getValue(), MediaType.APPLICATION_JSON_VALUE);
		return conn;
	}

	/**
	 * Receives
	 * @param text
	 * @param sourceLanguage
	 * @param targetLanguage
	 * @return the json object to send to the server as a request
	 */
	private JSONObject formRequest(String text, String sourceLanguage, String targetLanguage) {
		JSONObject request = new JSONObject();

		try {
			request.put(ConstantsEnum.TEXT.getValue(), text);
			request.put(ConstantsEnum.SOURCE_LANGUAGE.getValue(), sourceLanguage);
			request.put(ConstantsEnum.TARGET_LANGUAGE.getValue(), targetLanguage);

		} catch (JSONException e) {
			log.debug(e.getMessage());
		}
		return request;
	}


	/**
	 * Receives
	 * @param conn
	 * @param request
	 * reads input from the server and
	 * @return the new object with the translation request
	 * @throws IOException
	 */
	private TranslationResponse getTranslation(HttpsURLConnection conn, JSONObject request) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		OutputStream os = conn.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, ConstantsEnum.UTF_8.getValue()));
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
		return objectMapper.readValue(jsonResponse.toString(), TranslationResponse.class);
	}

	/**
	 * Receives HttpsURLConnection
	 * @param conn
	 * gets input from the server
	 * @return the new object with the translated text
	 * @throws IOException
	 */
	private TranslationResponse getTranslatedText(HttpsURLConnection conn) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), ConstantsEnum.UTF_8.getValue()));
		String inputLine;
		StringBuilder jsonResponse = new StringBuilder();

		while ((inputLine = in.readLine()) != null) {
			jsonResponse.append(inputLine);
		}
		in.close();
		return objectMapper.readValue(jsonResponse.toString(), TranslationResponse.class);
	}




}
