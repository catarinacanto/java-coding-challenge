package com.ccanto.unbabel.controllers;

import com.ccanto.unbabel.models.TranslationResponse;
import com.ccanto.unbabel.services.TranslationService;
import com.ccanto.unbabel.services.html.HtmlWriterService;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
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
	private HtmlWriterService htmlWriter;

	private List<TranslationResponse> responseList = new ArrayList<>();

	@RequestMapping(value = "/translate", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public RedirectView getTextToTranslate(@RequestParam(value = "text") String text, @RequestParam(value = "source_language") String sourceLanguage, @RequestParam(value = "target_language") String targetLanguage) throws IOException {
		TranslationResponse response = translationService.execute(text, sourceLanguage, targetLanguage);
		responseList.add(response);
		htmlWriter.generatePage(response);
		System.out.println(responseList.toString());
		return new RedirectView("/");
	}

	@RequestMapping(value = "/getTranslation", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public RedirectView getResponse() throws IOException {

		for (TranslationResponse response : responseList) {
			TranslationResponse newResponse = translationService.execute(response.getUid());
			System.out.println(newResponse.getTranslatedText());
			htmlWriter.generatePage(newResponse);
		}

		return new RedirectView("/");
	}

}
