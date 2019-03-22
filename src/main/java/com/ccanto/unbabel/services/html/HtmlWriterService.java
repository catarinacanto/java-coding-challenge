package com.ccanto.unbabel.services.html;

import com.ccanto.unbabel.models.Translation;
import com.ccanto.unbabel.services.AbstractWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
public class HtmlWriterService extends AbstractWriter {

	private Logger log = LogManager.getLogger(HtmlWriterService.class);

	/**
	 * Receives
	 *
	 * @param response and checks if there's already request for the translation
	 *                 with that uid on the html table. If so, checks for any updates on the translation,
	 *                 else it adds a new request on the table.
	 * @throws IOException
	 */
	public synchronized void generatePage(Translation response) throws IOException {
		read();
		if (!html.toString().contains(response.getUid())) {
			try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
				writer.write(parser.add(html.toString(), response));
			}
		} else {
			try (Writer outputWriter = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
				outputWriter.write(parser.update(html.toString(), response));
			}
		}
	}

	public synchronized void delete() throws IOException {
		read();
		try(Writer writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8.name())){
			writer.write(parser.delete(html.toString()));
		}

	}
}
