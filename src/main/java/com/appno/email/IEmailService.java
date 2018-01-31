package com.appno.email;

public interface IEmailService {
	void sendSimpleMessage( String to, String subject, String text);
}
