package io.swagger.api;


import java.security.NoSuchAlgorithmException;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.appno.dto.model.MySecretDataDTO;
import com.appno.services.IPersistence;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Errormsg;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-20T15:40:13.391-08:00")

@Controller
public class UnregisterApiController implements UnregisterApi {
	@Autowired
	private IPersistence persistance;



    public ResponseEntity<Object> unregisterPost( @NotNull @ApiParam(value = "This is user:password Base64 encoded pair of user which will be uregistered", required = true) @RequestHeader(value = "Authorization", required = true) String authorization,
        @ApiParam(value = "" ,required=false ) @RequestHeader(value="securetoken", required=false) String securetoken) {
    	HttpStatus status = HttpStatus.OK;
		Object retObj = "User was successefuly unregistered from the system";
		boolean isEmailToken = false;
    	
    	try {
    	if( securetoken == null  ){
    		if( authorization != null && persistance.readSecretsById(authorization.split(":")[0])!= null){
	    		MySecretDataDTO  mySecretData = new MySecretDataDTO(null, null, "Invalidate this Token");
	    		mySecretData.setDisabled(true);
				persistance.writeSecrets(authorization.split(":")[0], null,  mySecretData);
    		} else{
				status = HttpStatus.UNAUTHORIZED;
				retObj = status.getReasonPhrase();	
    		}
    		
    	} else if(  persistance.readSecretsByToken(securetoken, isEmailToken) == null  ) {
				status = HttpStatus.UNAUTHORIZED;
				retObj = status.getReasonPhrase();	

			} else {
	    		MySecretDataDTO  mySecretData = new MySecretDataDTO(null, null, "Invalidate this Token");
	    		mySecretData.setDisabled(true);
				persistance.writeSecrets(null, securetoken,  mySecretData);
			}
		} catch (NoSuchAlgorithmException  | AccessException e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			retObj = e.getMessage();	
		}
        return new ResponseEntity<Object>(retObj, status );
    }

}
