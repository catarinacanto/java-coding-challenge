package com.ccanto.unbabel.services.html;

import com.ccanto.unbabel.dataacess.TranslationResponse;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class HtmlWriterService extends AbstractWriter{

	public void generatePage(TranslationResponse response) throws IOException {
		read();
		try (FileWriter fileWriter = new FileWriter(file)) {
			if (html.toString().contains(response.getUid())){
				fileWriter.write(parser.update(html.toString(), response));
			}else {
				fileWriter.write(parser.parse(html.toString(), response));
			}
		}
	}


	public void delete(TranslationResponse response) throws IOException {
		read();
		try (FileWriter fileWriter = new FileWriter(file)) {
			if (html.toString().contains(response.getUid())){
				fileWriter.write(parser.delete(html.toString(), response));
			}
		}

	}
}
