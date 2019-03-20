package com.ccanto.unbabel.services.html;

import com.ccanto.unbabel.dataacess.TranslationResponse;
import com.ccanto.unbabel.services.AbstractWriter;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class HtmlWriterService extends AbstractWriter {

	/**
	 * Receives
	 * @param response and checks if there's already request for the translation
	 * with that uid on the html table. If so, checks for any updates on the translation,
	 * else it adds a new request on the table.
	 * @throws IOException
	 */
	public void generatePage(TranslationResponse response) throws IOException {
		read();
		try (FileWriter fileWriter = new FileWriter(file)) {
			if (html.toString().contains(response.getUid())){
				fileWriter.write(parser.update(html.toString(), response));
			}else {
				fileWriter.write(parser.add(html.toString(), response));
			}
		}
	}

	/**
	 * Receives
	 * @param uid and checks if there's already request or translation with that uid on
	 * the html table, if so, deletes it
	 * @throws IOException
	 */
	public void delete(String uid) throws IOException {
		read();
		try (FileWriter fileWriter = new FileWriter(file)) {
			if (html.toString().contains(uid)){
				fileWriter.write(parser.delete(html.toString(), uid));
			}
		}

	}
}
