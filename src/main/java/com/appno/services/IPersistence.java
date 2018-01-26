package com.appno.services;


public interface IPersistence {
	void writeSecrets(String userId, Object password);
	Object readSecretsById(String userId);
	Object readSecretsByToken(String secureToken);
}
