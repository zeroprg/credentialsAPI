package com.appno.services;

import java.security.NoSuchAlgorithmException;

import org.springframework.expression.AccessException;

public interface IPersistence  {
	void writeSecrets(String userId, String secureToken, Object secureDTO) throws NoSuchAlgorithmException,AccessException;
	Object readSecretsById(String userId) throws AccessException;
	Object readSecretsByToken(String secureToken) throws NoSuchAlgorithmException,AccessException;
}
