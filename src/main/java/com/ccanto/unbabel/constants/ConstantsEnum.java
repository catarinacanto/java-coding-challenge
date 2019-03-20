package com.ccanto.unbabel.constants;

public enum  ConstantsEnum {
	SANDBOX_URL("https://sandbox.unbabel.com/tapi/v2/translation/"),
	API_KEY("ApiKey fullstack-challenge:9db71b322d43a6ac0f681784ebdcc6409bb83359"),
	AUTHORIZATION("Authorization"),
	CONTENT_TYPE("Content-Type"),
	TEXT("text"),
	SOURCE_LANGUAGE("source_language"),
	TARGET_LANGUAGE("target_language"),
	TABLE("table"),
	TBODY("tbody"),
	TR("tr"),
	UID("uid"),
	TD("td"),
	TRANSLATED("translated"),
	STATUS("status"),
	UTF_8("UTF-8");

	private String value;
	ConstantsEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}


