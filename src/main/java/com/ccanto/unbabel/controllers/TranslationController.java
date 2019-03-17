package com.ccanto.unbabel.controllers;

import com.ccanto.unbabel.services.translationService.TranslationService;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@RestController
public class TranslationController {

	@Autowired
	TranslationService translationService;



	@RequestMapping(value = "/translate", produces = MediaType.APPLICATION_JSON_VALUE  + ";charset=UTF-8")
	public void getTextToTranslate(@RequestParam(value = "text") String text, @RequestParam(value = "language") String language) throws IOException, JSONException {
		URL url = new URL("https://sandbox.unbabel.com/tapi/v2/translation/");
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setReadTimeout(30000);
		conn.setConnectTimeout(15000);
		conn.setRequestMethod("POST");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Authorization", "ApiKey fullstack-challenge:9db71b322d43a6ac0f681784ebdcc6409bb83359");
		conn.setRequestProperty("Content-Type", "application/json");

		JSONObject request = new JSONObject();
		request.put("text", text);
		request.put("target_language", language);
		request.put("source_language", "en");

		OutputStream os = conn.getOutputStream();
		BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(os, "UTF-8"));
		writer.write(request.toString());
		writer.flush();
		writer.close();
		os.close();

		System.out.println(conn.getInputStream());
		conn.connect();
	}


}
