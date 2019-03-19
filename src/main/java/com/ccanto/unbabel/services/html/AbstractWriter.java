package com.ccanto.unbabel.services.html;

import java.io.*;

public abstract class AbstractWriter {

	protected File file;
	protected StringBuilder html;
	protected String s;
	protected HtmlParserService parser;

	protected void read() throws IOException {
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
