package com.ccanto.unbabel.controllers;

import com.ccanto.unbabel.constants.ConstantsEnum;
import com.ccanto.unbabel.dataacess.TranslationRepository;
import com.ccanto.unbabel.dataacess.TranslationResponse;
import com.ccanto.unbabel.services.translation.TranslationService;
import com.ccanto.unbabel.services.html.HtmlWriterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RestController
public class TranslationController {

	@Autowired
	private TranslationService translationService;

	@Autowired
	private TranslationRepository repository;

	@Autowired
	private HtmlWriterService htmlWriter;
	private Logger log = LogManager.getLogger(TranslationController.class);
	private List<TranslationResponse> responseList = new ArrayList();


	/**
	 * When a user requests a new translation by clicking "submit" a new request for a translation is made to unbabel sandbox api,
	 * this method receives
	 *
	 * @param text
	 * @param sourceLanguage
	 * @param targetLanguage
	 * @return a view to the same page with the current request added to the table and status = "new"
	 * the request is also added to the database on the table "translation response"
	 */
	@RequestMapping(value = "/translate")
	public RedirectView requestTranslation(@RequestParam(value = "text") String text, @RequestParam(value = "source_language") String sourceLanguage, @RequestParam(value = "target_language") String targetLanguage) {
		TranslationResponse response;
		try {
			if (text != null && targetLanguage != null && sourceLanguage != null) {
				response = translationService.execute(text, sourceLanguage, targetLanguage);
				response.setCreate_date(String.valueOf(LocalDateTime.now()));
				repository.save(response);
				responseList.add(response);
				htmlWriter.generatePage(response);
			}
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
		update();
		return new RedirectView("/");
	}

	/**
	 * When a user clicks the button "update" a new request is sent to unbabel sandbox api
	 * to check if there's been any updates on the translation, e.g.: if the status has been changed
	 * if so,
	 *
	 * @return a view to the same page, but with all the updates that have been made to the translation
	 * and with the translated text if the status is now "complete", the information on the database is
	 * also updated
	 */
	@RequestMapping(value = "/getTranslation")
	public RedirectView update() {
		try {
			for (TranslationResponse response : repository.findAll()) {
				TranslationResponse newResponse = translationService.execute(response.getUid());
				if (!newResponse.getStatus().equals(response.getStatus())) {
					response.setStatus(newResponse.getStatus());
					response.setTranslatedText(newResponse.getTranslatedText());
					response.setUpdate_date(String.valueOf(LocalDateTime.now()));
					repository.save(response);
					htmlWriter.generatePage(newResponse);
				}
			}
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
		return new RedirectView("/");
	}

	/**
	 * When a user clicks the button "delete"
	 *
	 * @return a view to the same page with all the requests and translations deleted
	 * all the requests are still saved on the database
	 */
	@RequestMapping(value = "/delete")
	public RedirectView delete() {
		try {
			for (TranslationResponse response : responseList) {
				if (!responseList.isEmpty()) {
					htmlWriter.delete(response.getUid());
				}
			}
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
		return new RedirectView("/");
	}

}
