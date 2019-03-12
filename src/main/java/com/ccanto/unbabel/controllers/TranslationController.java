package com.ccanto.unbabel.controllers;

import com.ccanto.unbabel.services.translationService.TranslationResponse;
import com.ccanto.unbabel.services.translationService.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TranslationController {

	@Autowired TranslationService translationService;

	@RequestMapping(value = "/translate", method = RequestMethod.POST, produces = {"application/json"})
	public TranslationResponse getTextToTranslate(@RequestParam(value = "text") String text, @RequestParam(value = "language") String language){
		return translationService.requestTranslation(text, language);
	}
}
