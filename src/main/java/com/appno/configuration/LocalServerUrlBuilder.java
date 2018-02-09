package com.appno.configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LocalServerUrlBuilder {

	private String host;


	@Value("${server.contextPath}")
	private String contextPath;
	@Value("${server.protocol}")
	private String protocol;
	@Value("${server.host}")
	private String host_provided;
	
    @Autowired
    private org.springframework.boot.autoconfigure.web.ServerProperties serverProperties;
	
	private String url;

	public String getUrl() {
		try {
			if("${server.host}".equals(host_provided)){ 
				host = InetAddress.getLocalHost().getHostName();
				if(host.startsWith("ip-")) host = InetAddress.getLocalHost().getHostAddress(); //host.replace("ip-", "").replace('-' , '.');
			} else {
				host = host_provided;
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		url = protocol + "://" + host  + contextPath;
		return url;
	}

}
