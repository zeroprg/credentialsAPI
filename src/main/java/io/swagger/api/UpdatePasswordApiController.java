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
import io.swagger.model.Errormsg;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-24T21:44:51.228-08:00")

@Controller
public class UpdatePasswordApiController implements UpdatePasswordApi {

    @Autowired
	private IPasswordsTool passwordTool;  
    @Autowired
    private IPersistence persistance;

    public ResponseEntity<Object> updatePasswordPost(@ApiParam(value = "" ,required=true ) @RequestHeader(value="Authorization", required=true) String authorization,
        @ApiParam(value = "" ,required=true ) @RequestHeader(value="newpassword", required=true) String newpassword) {
    	String credentials[] = authorization.split(":");
    	MySecretDataDTO mySecretDataDTO = ((MySecretDataDTO) persistance.readSecretsById(credentials[0]));
 	    String vaultPassword = mySecretDataDTO.getPassword();
    			
    			
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		String secureToken = null;
		Object retObj;
		
		if (credentials[1].equals(vaultPassword)) {
			status = HttpStatus.OK;
			retObj = secureToken;
		} else {
			Errormsg error = new Errormsg();
			error.setCode(HttpStatus.UNAUTHORIZED.value());
			error.setMsg(HttpStatus.UNAUTHORIZED.getReasonPhrase());
			retObj = error;
	        return new ResponseEntity<Object>(retObj, HttpStatus.UNAUTHORIZED);
		}
    	
    	
    	// Update user's password in Persistanse Layer
		mySecretDataDTO.setPassword(newpassword);
	   	// generate new  secure token which will be use to authenticate by default in browser
    	String newsecureToken = passwordTool.generatePassPhrase();
		mySecretDataDTO.setSecureToken(newsecureToken);
    	
    	persistance.writeSecrets(credentials[0], mySecretDataDTO);
    	
        return new ResponseEntity<Object>(secureToken, HttpStatus.OK);
    }

}
