package com.ccanto.unbabel.services.html;

import com.ccanto.unbabel.constants.ConstantsEnum;
import com.ccanto.unbabel.dataacess.TranslationResponse;
import com.ccanto.unbabel.models.Translation;
import com.ccanto.unbabel.services.AbstractWriter;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
public class HtmlWriterService extends AbstractWriter {

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
		Writer fstream = null;
		try {
			fstream = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
			if (!html.toString().contains(response.getUid())) {
				fstream.write(parser.add(html.toString(), response));
			} else {
				fstream.write(parser.update(html.toString(), response));
			}
		}catch (IOException e){
			e.printStackTrace();
		} finally {
			if (fstream!= null)
			fstream.close();
		}

	}

}
