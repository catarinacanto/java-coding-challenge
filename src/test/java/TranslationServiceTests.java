import com.ccanto.unbabel.constants.ConstantsEnum;
import com.ccanto.unbabel.dataacess.TranslationResponse;
import com.ccanto.unbabel.services.translation.TranslationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.UUID;

public class TranslationServiceTests {

	TranslationService translationService = new TranslationService();
	private String uid;
	private String text;
	private String sourceLanguage;
	private String targetLanguage;
	private JSONObject request;
	private URL url;
	private ObjectMapper objectMapper = new ObjectMapper();

	@Before
	public void setUp() throws Exception {
		uid = UUID.randomUUID().toString().substring(0,7);
		text = "hello";
		sourceLanguage = "en";
		targetLanguage = "pt";
		request = new JSONObject();
		url = new URL(ConstantsEnum.SANDBOX_URL.getValue());
	}

	@Test
	public void executeFirstRequest() throws IOException, JSONException {
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setReadTimeout(100000);
		conn.setConnectTimeout(15000);
		conn.setRequestMethod("POST");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty(ConstantsEnum.AUTHORIZATION.getValue(), ConstantsEnum.API_KEY.getValue());
		conn.setRequestProperty(ConstantsEnum.CONTENT_TYPE.getValue(), MediaType.APPLICATION_JSON_VALUE);

		request.put(ConstantsEnum.UID.getValue(), uid);
		request.put(ConstantsEnum.TEXT.getValue(), text);
		request.put(ConstantsEnum.SOURCE_LANGUAGE.getValue(), sourceLanguage);
		request.put(ConstantsEnum.TARGET_LANGUAGE.getValue(), targetLanguage);

		System.out.println(request);
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
		TranslationResponse expected = objectMapper.readValue(jsonResponse.toString(), TranslationResponse.class);
		String newUid = UUID.randomUUID().toString().substring(0,7);
		TranslationResponse actual = translationService.execute(text, sourceLanguage, targetLanguage, newUid);

		Assert.assertEquals(expected.getSource_language(), actual.getSource_language());
		Assert.assertEquals(expected.getTarget_language(), actual.getTarget_language());
		Assert.assertEquals(expected.getText(), actual.getText());
		Assert.assertEquals(expected.getStatus(), actual.getStatus());
	}

	@Test
	public void executeSecondRequest() throws IOException {
		String newUid = "4699c03b37";
		url = new URL(ConstantsEnum.SANDBOX_URL.getValue() + newUid + "/");
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setReadTimeout(100000);
		conn.setConnectTimeout(15000);
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty(ConstantsEnum.AUTHORIZATION.getValue(), ConstantsEnum.API_KEY.getValue());
		conn.setRequestProperty(ConstantsEnum.CONTENT_TYPE.getValue(), MediaType.APPLICATION_JSON_VALUE);

		ObjectMapper objectMapper = new ObjectMapper();
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), ConstantsEnum.UTF_8.getValue()));
		String inputLine;
		StringBuilder jsonResponse = new StringBuilder();

		while ((inputLine = in.readLine()) != null) {
			jsonResponse.append(inputLine);
		}
		in.close();
		TranslationResponse expected = objectMapper.readValue(jsonResponse.toString(), TranslationResponse.class);
		TranslationResponse actual = translationService.execute(newUid);

		Assert.assertEquals(expected.getStatus(), actual.getStatus());
		Assert.assertEquals(expected.getTranslatedText(), actual.getTranslatedText());
		Assert.assertEquals(expected.getUid(), actual.getUid());
	}
}
