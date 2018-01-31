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



    public ResponseEntity<Object> unregisterGet( @NotNull @ApiParam(value = "This is user:password Base64 encoded pair of user which will be uregistered", required = true) @RequestParam(value = "Authorization", required = true) String Authorization,
        @ApiParam(value = "" ,required=true ) @RequestHeader(value="securetoken", required=true) String securetoken) {
    	HttpStatus status = HttpStatus.OK;
		Object retObj = "User was successefuly unregistered from the system";
		boolean isEmailToken = false;
    	
    	try {
			if(  persistance.readSecretsByToken(securetoken, isEmailToken) == null ) {
/*				Errormsg error = new Errormsg();
				error.setCode(HttpStatus.UNAUTHORIZED.value());
				error.setMsg(HttpStatus.UNAUTHORIZED.getReasonPhrase());
				retObj = error;
				status = HttpStatus.UNAUTHORIZED;*/
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
