package com.ccanto.unbabel;

public class Authorization {

	private static Authorization authorization = new Authorization();
	private static final String username = "fullstack-challenge";
	private static final String apiKey = "9db71b322d43a6ac0f681784ebdcc6409bb83359";
	private Authorization() {
	}


	public static Authorization getInstance(){
		return authorization;
	}

	@Override
	public String toString() {
		return "ApiKey " + username + ":" + apiKey;
	}


}
