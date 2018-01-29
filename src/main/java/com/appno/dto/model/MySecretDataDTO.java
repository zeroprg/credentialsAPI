package com.appno.dto.model;

public  class MySecretDataDTO {

	   
	String password;
	String eMail;
	String secureToken;
	Boolean validationByEmailToken;
	Integer validInMinutes;
	Boolean active = true;
	Boolean admin = false;
	Boolean newInstance = false;


	public Boolean getNewInstance() {
		return newInstance;
	}

	public void setNewInstance(Boolean newInstance) {
		this.newInstance = newInstance;
	}

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
		this.active = true;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return eMail;
	}

	public void setEmail(String eMail) {
		this.eMail = eMail;
	}

	public Integer getValidInMinutes() {
		return validInMinutes;
	}

	public void setValidInMinutes(Integer validInMinutes) {
		this.validInMinutes = validInMinutes;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Boolean getValidationByEmailToken() {
		return validationByEmailToken;
	}

	public void setValidationByEmailToken(Boolean validationByEmailToken) {
		this.validationByEmailToken = validationByEmailToken;
	}
	
}

