package com.ccanto.unbabel.services.html;

import com.ccanto.unbabel.dataacess.TranslationResponse;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class HtmlWriterService {

	public void generatePage(TranslationResponse response) throws IOException {
		String s;
		StringBuilder html = new StringBuilder();
		File file = new File("src/main/webapp/index.html");
		HtmlParser parser = new HtmlParser();
		try (FileReader fileReader = new FileReader(file)) {
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((s = bufferedReader.readLine()) != null) {
				html.append(s);
			}
		}
		try (FileWriter fileWriter = new FileWriter(file)) {
			if (html.toString().contains(response.getUid())){
				fileWriter.write(parser.update(html.toString(), response));
			}else {
				fileWriter.write(parser.parse(html.toString(), response));
			}
		}
	}


	public void delete(TranslationResponse response) throws IOException {
		String s;
		StringBuilder html = new StringBuilder();
		File file = new File("src/main/webapp/index.html");
		HtmlParser parser = new HtmlParser();
		try (FileReader fileReader = new FileReader(file)) {
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((s = bufferedReader.readLine()) != null) {
				html.append(s);
			}
		}
		try (FileWriter fileWriter = new FileWriter(file)) {
			if (html.toString().contains(response.getUid())){
				fileWriter.write(parser.delete(html.toString(), response));
			}
		}

	}
}
