package com.ccanto.unbabel.controllers;

import com.ccanto.unbabel.dataacess.TranslationRepository;
import com.ccanto.unbabel.dataacess.TranslationResponse;
import com.ccanto.unbabel.services.TranslationService;
import com.ccanto.unbabel.services.html.HtmlWriterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.*;
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


	@RequestMapping(value = "/translate", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public RedirectView getTextToTranslate(@RequestParam(value = "text") String text, @RequestParam(value = "source_language") String sourceLanguage, @RequestParam(value = "target_language") String targetLanguage) {
		TranslationResponse response = null;
		try {
			response = translationService.execute(text, sourceLanguage, targetLanguage);
			repository.save(response);
			htmlWriter.generatePage(response);
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
		return new RedirectView("/");
	}

	@RequestMapping(value = "/getTranslation", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public RedirectView getResponse() {
		try {
			for (TranslationResponse response : repository.findAll()) {
				TranslationResponse newResponse = translationService.execute(response.getUid());
				response.setStatus(newResponse.getStatus());
				response.setTranslatedText(newResponse.getTranslatedText());
				repository.save(response);
				htmlWriter.generatePage(newResponse);
			}
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
		return new RedirectView("/");
	}

	@RequestMapping(value = "/delete")
	public RedirectView delete() {
		try {
			for (TranslationResponse response : repository.findAll()) {
				TranslationResponse newResponse = translationService.execute(response.getUid());
				htmlWriter.delete(newResponse);
			}
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
		return new RedirectView("/");
	}

}
