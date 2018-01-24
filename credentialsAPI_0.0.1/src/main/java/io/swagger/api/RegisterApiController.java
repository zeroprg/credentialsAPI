package io.swagger.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;


import com.appno.services.IPasswordsTool;
import com.appno.services.IVaultTool;
import com.appno.services.impl.VaultTool;

import io.swagger.annotations.ApiParam;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-20T15:40:13.391-08:00")

@Controller
public class RegisterApiController implements RegisterApi {

    @Autowired
	private IPasswordsTool passwordTool;  
    @Autowired
    private IVaultTool vaultTool;

	public ResponseEntity<Object> authPasswordPost(
			@ApiParam(value = "Send Base64 encoded user:password pair", required = true)
			@RequestHeader(value = "Authorization", required = true) String authorization) {
    	
    	String credentials[] = authorization.split(":");
    	
    	// generate secure token which will be use to authenticate in Syncope
    	String secureToken = passwordTool.generatePassPhrase();
    	
    	// Store user's password in Vault
    	VaultTool.MySecretData  mySecretData = new VaultTool.MySecretData(credentials[0], credentials[1]);
    	
    	vaultTool.writeSecrets(credentials[0], mySecretData);
    	
        return new ResponseEntity<Object>(secureToken, HttpStatus.OK);
    }

}
