package com.ccanto.unbabel.controllers;

import com.ccanto.unbabel.models.TranslationResponse;
import com.ccanto.unbabel.services.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.RedirectViewControllerRegistration;
import org.springframework.web.servlet.view.RedirectView;

import java.io.*;


@RestController
public class TranslationController {

	@Autowired
	TranslationService translationService;


	@RequestMapping(value = "/translate", produces = MediaType.APPLICATION_JSON_VALUE  + ";charset=UTF-8")
	public RedirectView getTextToTranslate(@RequestParam(value = "text") String text, @RequestParam(value = "source_language") String sourceLanguage, @RequestParam(value = "target_language") String targetLanguage) throws IOException {
		translationService.execute(text, sourceLanguage, targetLanguage);

		return new RedirectView("/");
	}


}
