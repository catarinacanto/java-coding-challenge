package com.ccanto.unbabel.services.html;

import com.ccanto.unbabel.constants.ConstantsEnum;
import com.ccanto.unbabel.dataacess.TranslationResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class HtmlParserService {

	private Document doc;

	/**
	 * Receives
	 * @param html
	 * @param response
	 * and appends new elements to the table body
	 * @return the new document to be writen by the HtmlWriterService
	 */
	public String add(String html, TranslationResponse response) {
		doc = Jsoup.parse(html, ConstantsEnum.UTF_8.getValue());
		Element tBody = doc.getElementsByTag(ConstantsEnum.TBODY.getValue()).get(0);
		Element row = tBody.appendElement(ConstantsEnum.TR.getValue()).attr(ConstantsEnum.UID.getValue(), response.getUid());
		row.appendElement(ConstantsEnum.TD.getValue()).text(response.getSource_language());
		row.appendElement(ConstantsEnum.TD.getValue()).text(response.getText());
		row.appendElement(ConstantsEnum.TD.getValue()).text(response.getTarget_language());
		row.appendElement(ConstantsEnum.TD.getValue()).attr(ConstantsEnum.TRANSLATED.getValue(), "").text(response.getTranslatedText() == null ? "" : response.getTranslatedText());
		row.appendElement(ConstantsEnum.TD.getValue()).attr(ConstantsEnum.STATUS.getValue(), response.getStatus()).text(response.getStatus());

		return doc.html();
	}

	public String update(String html, TranslationResponse response) {
		doc = Jsoup.parse(html, ConstantsEnum.UTF_8.getValue());
		Elements rows = doc.getElementsByTag(ConstantsEnum.TR.getValue());
		for (Element row : rows) {
			Elements translation = row.getElementsByAttributeValue(ConstantsEnum.UID.getValue(), response.getUid());
			for (Element element : translation) {
				element.getElementsByAttribute(ConstantsEnum.STATUS.getValue()).get(0).text(response.getStatus());
				element.getElementsByAttribute(ConstantsEnum.TRANSLATED.getValue()).get(0).text(response.getTranslatedText() != null ? String.valueOf(response.getTranslatedText()) : "");
			}
		}

		return doc.html();
	}

	public String delete(String html, String uid) {
		doc = Jsoup.parse(html, ConstantsEnum.UTF_8.getValue());
		Elements rows = doc.getElementsByTag(ConstantsEnum.TR.getValue());
		for (Element row : rows) {
			Elements translation = row.getElementsByAttributeValue(ConstantsEnum.UID.getValue(), uid);
			for (Element element : translation) {
				element.remove();
			}
		}
		return doc.html();
	}

}
