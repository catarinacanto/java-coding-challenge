package com.ccanto.unbabel.services.html;

import com.ccanto.unbabel.constants.ConstantsEnum;
import com.ccanto.unbabel.models.TranslationResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class HtmlParser {

	private Document doc;
	private Element table;
	private Element tBody;

	public String parse(String html, TranslationResponse response) {
		doc = Jsoup.parse(html, ConstantsEnum.UTF_8.getValue());
		table = doc.getElementsByTag("table").get(0);
		tBody = table.appendElement("tbody").attr("uid", response.getUid());
		tBody.appendElement("td").text(response.getSource_language());
		tBody.appendElement("td").text(response.getText());
		tBody.appendElement("td").text(response.getTarget_language());
		tBody.appendElement("td").attr("translated", "").text(response.getTranslatedText() == null ? "" : response.getTranslatedText());
		tBody.appendElement("td").attr("status", response.getStatus()).text(response.getStatus());

		return doc.html();
	}

	public String update(String html, TranslationResponse response) {
		doc = Jsoup.parse(html, ConstantsEnum.UTF_8.getValue());
		Elements tbody = doc.getElementsByTag("tbody");
		for (Element body : tbody) {
			Elements translation = body.getElementsByAttributeValue("uid", response.getUid());
			for (Element element : translation) {
				element.getElementsByAttribute("status").get(0).text(response.getStatus());
				element.getElementsByAttribute("translated").get(0).text(response.getTranslatedText() != null ? response.getTranslatedText() : "");
			}
		}

		return doc.html();
	}
}
