package com.ccanto.unbabel.controllers;

import com.ccanto.unbabel.constants.ConstantsEnum;
import com.ccanto.unbabel.dataacess.TranslationRepository;
import com.ccanto.unbabel.dataacess.TranslationResponse;
import com.ccanto.unbabel.models.Translation;
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
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


@RestController
public class TranslationController {

	@Autowired
	private TranslationService translationService;

	@Autowired
	private TranslationRepository repository;

	@Autowired
	private HtmlWriterService htmlWriter;

	private Logger log = LogManager.getLogger(TranslationController.class);

	private ExecutorService executor = Executors.newSingleThreadExecutor();

	private List<TranslationResponse> translationList = new ArrayList<>();


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
		Translation translation = new Translation();
		String uid = UUID.randomUUID().toString();
		translation.setFrom(sourceLanguage).setOriginal(text).setTo(targetLanguage).setStatus("new").setUid(uid.substring(0, 7) + uid.substring(9, 12));
		try {
			htmlWriter.generatePage(translation);
		} catch (IOException e) {
			log.error("Error generating html", e);
		}
		executor.submit(() -> sendRequest(translation));
		return new RedirectView("/");
	}

	private synchronized void sendRequest(Translation translation) {
		TranslationResponse response;
		try {
			response = translationService.execute(translation.getOriginal(), translation.getFrom(), translation.getTo(), translation.getUid());
			response.setCreate_date(String.valueOf(LocalDateTime.now()));
			translationList.add(response);
			repository.save(response);

		} catch (IOException e) {
			log.debug(e.getMessage());
		}
	}

	private void updateRequest(String uid) {
		TranslationResponse response;
		try {
			response = translationService.execute(uid);
			repository.save(response);
		} catch (IOException e) {
			log.error("Error updating request", e);
		}
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
	public synchronized RedirectView update() {
		try {
			for (TranslationResponse response : translationList) {
				Translation translation = new Translation();

				TranslationResponse newResponse = translationService.execute(response.getUid());
				if (!newResponse.getStatus().equals(response.getStatus())) {
					response.setStatus(newResponse.getStatus()).setTranslatedText(newResponse.getTranslatedText()).setUpdate_date(String.valueOf(LocalDateTime.now()));
					repository.save(response);
					translation.setUid(response.getUid()).setStatus(response.getStatus()).setTranslated(response.getTranslatedText());
					htmlWriter.generatePage(translation);
				}
			}
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
		return new RedirectView("/");
	}

}
