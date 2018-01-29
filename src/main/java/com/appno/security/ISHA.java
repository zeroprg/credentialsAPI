package com.appno.security;

import java.security.NoSuchAlgorithmException;

public interface ISHA {
	public String hash(String password) throws NoSuchAlgorithmException;
}
