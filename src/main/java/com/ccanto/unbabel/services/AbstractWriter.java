package com.ccanto.unbabel.services;

import com.ccanto.unbabel.constants.ConstantsEnum;
import com.ccanto.unbabel.services.html.HtmlParserService;
import sun.misc.CharacterEncoder;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 *  * Abstract class to be extended to any class that writes in the "index.html" file
 */
public abstract class AbstractWriter {

	protected File file;
	protected StringBuilder html;
	protected HtmlParserService parser;

	/**
	 * Initializes and reads the current html page
	 * @throws IOException
	 */
	protected void read() throws IOException {
		String s;
		html = new StringBuilder();
		file = new File("src/main/webapp/index.html");
		parser = new HtmlParserService();
		try (FileInputStream fileReader = new FileInputStream(file)) {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileReader,StandardCharsets.UTF_8.name()));
			while ((s = bufferedReader.readLine()) != null) {
				html.append(s);
			}
		}
	}
}
