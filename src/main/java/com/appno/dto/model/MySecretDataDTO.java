package com.appno.dto.model;

public  class MySecretDataDTO {

	   
	String password;
	String eMail;
	String secureToken;
	Boolean validationByEmailToken;
	Integer validInMinutes;
	Boolean active = true;
	//Boolean admin = false;
	Boolean disabled = false;
	Boolean newInstance = false;
    String IP;

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
/*	
	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
*/
	public Boolean getValidationByEmailToken() {
		return validationByEmailToken;
	}

	public void setValidationByEmailToken(Boolean validationByEmailToken) {
		this.validationByEmailToken = validationByEmailToken;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disable) {
		this.disabled = disable;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}
	
}

