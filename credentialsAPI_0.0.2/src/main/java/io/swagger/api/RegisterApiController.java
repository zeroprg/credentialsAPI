package io.swagger.api;


import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
	private IPasswordsTool passwordTool;  
    @Autowired
    private IPersistence persistance;

	public ResponseEntity<Object> registerPost(
			@ApiParam(value = "Send Base64 encoded user:password pair", required = true)
			@RequestHeader(value = "Authorization", required = true) String authorization) {
    	
    	String credentials[] = authorization.split(":");
    	
    	// generate secure token which will be use to authenticate in Syncope
    	String secureToken = passwordTool.generatePassPhrase();
    	
    	// Store user's password in Persistanse Layer
    	MySecretDataDTO  mySecretData = new MySecretDataDTO(credentials[0], credentials[1], secureToken);
    	
    	persistance.writeSecrets(credentials[0], mySecretData);
    	
        return new ResponseEntity<Object>(secureToken, HttpStatus.OK);
    }
	
}
