package com.ccanto.unbabel.services;

import com.ccanto.unbabel.services.html.HtmlParserService;

import java.io.*;

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
		try (FileReader fileReader = new FileReader(file)) {
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((s = bufferedReader.readLine()) != null) {
				html.append(s);
			}
		}
	}
}
