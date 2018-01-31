package com.appno.configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LocalServerUrlBuilder {

	private String host;

	@Value("${server.port}")
	private String serverPort;
	@Value("${server.contextPath}")
	private String contextPath;
	@Value("${server.protocol}")
	private String protocol;
	@Value("${server.host}")
	private String host_provided;
	
	private String url;

	public String getUrl() {
		try {
			if(host_provided == null){ 
			host = InetAddress.getLocalHost().getHostName();
			
			if(host.startsWith("ip-")) host = InetAddress.getLocalHost().getHostAddress(); //host.replace("ip-", "").replace('-' , '.');
			} else {
				host = host_provided;
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		url = protocol + "://" + host + ":" + serverPort  + contextPath;
		return url;
	}

}
