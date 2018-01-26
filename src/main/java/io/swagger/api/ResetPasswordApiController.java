package io.swagger.api;

import io.swagger.model.Errormsg;

import io.swagger.annotations.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.appno.persistance.entities.MySecretData;
import com.appno.services.IPersistence;

import java.util.List;

import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-24T21:44:51.228-08:00")

@Controller
public class ResetPasswordApiController implements ResetPasswordApi {
    private static final Object RESET_PASSWORD_MSG = "eMail to reset password was sent";
	@Autowired
    private IPersistence persistance;


    public ResponseEntity<Object> resetPasswordPost(@ApiParam(value = "" ,required=true ) @RequestHeader(value="securetoken", required=true) String securetoken) {
    	HttpStatus status = HttpStatus.OK;
		Object retObj = securetoken;
		
		MySecretData mySecretData = (MySecretData)persistance.readSecretsByToken(securetoken);
    	
    	if( mySecretData == null ) {
    		Errormsg error = new Errormsg();
			error.setCode(HttpStatus.UNAUTHORIZED.value());
			error.setMsg(HttpStatus.UNAUTHORIZED.getReasonPhrase());
			retObj = error;
			status = HttpStatus.UNAUTHORIZED;
    	} else {
    		// initialize eMail to customer
    		String eMail =  mySecretData.getEmail();
    		status = HttpStatus.OK;
    		retObj = RESET_PASSWORD_MSG; 
    	}
    	
        return new ResponseEntity<Object>(retObj, status );
    }

}
