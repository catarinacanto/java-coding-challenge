package com.ccanto.unbabel.services.translationService;

import com.ccanto.unbabel.services.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class TranslationService extends AbstractService{

	public TranslationResponse requestTranslation(String text, String targetLanguage){
		TranslationRequest request = new TranslationRequest();
		System.out.println(request);
		initialize();
		request.setTargetLanguage(targetLanguage).setText(text);
		return new TranslationResponse();
	}
}
