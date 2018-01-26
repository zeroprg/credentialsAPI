package com.appno.dto.model;


public  class MySecretDataDTO {
	String password;
	String eMail;
	String secureToken;
	
	public MySecretDataDTO(){}
	
	public String getSecureToken() {
		return secureToken;
	}

	public void setSecureToken(String securedToken) {
		this.secureToken = securedToken;
	}

	public MySecretDataDTO( String eMail, String password, String secureToken ) {
		this.password = password;
		this.eMail = eMail;	
		this.secureToken = secureToken;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEMail() {
		return eMail;
	}

	public void setEMail(String eMail) {
		this.eMail = eMail;
	}
}

