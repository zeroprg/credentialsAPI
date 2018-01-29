package io.swagger.api;


import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;

import com.appno.dto.model.MySecretDataDTO;
import com.appno.services.IPasswordsTool;
import com.appno.services.IPersistence;

import io.swagger.annotations.ApiParam;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-20T15:40:13.391-08:00")

@Controller
public class RegisterApiController implements RegisterApi {

    private static final Object USER_CREATED = "User was created";
	@Autowired
	private IPasswordsTool passwordTool;  
    @Autowired
    private IPersistence persistance;

	public ResponseEntity<Object> registerPost(
			@ApiParam(value = "Send Base64 encoded user:password pair", required = true)
			@RequestHeader(value = "Authorization", required = true) String authorization) {
    	
    	String credentials[] = authorization.split(":");
    	
    	// generate secure token which will be use to authenticate by default in browser
    	String secureToken = passwordTool.generatePassPhrase();
		HttpStatus status = HttpStatus.OK;
		Object retObj = USER_CREATED;

    	
    	try {  	
    	// Store user's password in Persistanse Layer
    		MySecretDataDTO  mySecretData = new MySecretDataDTO(credentials[0], credentials[1], secureToken);
    		mySecretData.setNewInstance(true);
			persistance.writeSecrets(credentials[0], null,  mySecretData);
			retObj = secureToken;
		} catch (NoSuchAlgorithmException  | AccessException e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			retObj = e.getMessage();	
		}	
        return new ResponseEntity<Object>(retObj, status);
    }
	
}
