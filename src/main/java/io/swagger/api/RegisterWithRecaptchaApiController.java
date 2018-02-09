package io.swagger.api;


import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.appno.dto.model.MySecretDataDTO;
import com.appno.services.IPasswordsTool;
import com.appno.services.IPersistence;
import com.appno.services.impl.RecaptchaService;

import io.swagger.annotations.ApiParam;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-20T15:40:13.391-08:00")

@Controller
public class RegisterWithRecaptchaApiController implements RegisterWithRecaptchaApi {

    private static final Object USER_CREATED = "User was already created";
	private static final Object BAD_RECAPTCHA = "You identified as robot: ";
	@Autowired 
	RecaptchaService captchaService;
	@Autowired
	private IPasswordsTool passwordTool;  
    @Autowired
    private IPersistence persistance;

	public ResponseEntity<Object> registerPost(
    		@ApiParam(value = "" ,required=true ) @RequestParam(value="email", required=true) String email,
    		@ApiParam(value = "" ,required=true ) @RequestParam(value="password", required=true) String password,
    		@ApiParam(value = "" ,required=true ) @RequestParam(name="g-recaptcha-response") String recaptchaResponse,
    		@ApiParam(value = "" ,required=false ) @RequestParam(name="ip-address") String ip)
	{
    	
    //	String credentials[] = authorization.split(":");
    	
    	// generate secure token which will be use to authenticate by default in browser
    	String secureToken = passwordTool.generatePassPhrase();
		HttpStatus status = HttpStatus.OK;
		Object retObj = USER_CREATED;

		String captchaVerifyMessage = 
				captchaService.verifyRecaptcha(ip, recaptchaResponse);
			
		if ( captchaVerifyMessage != null) {
			status = HttpStatus.EXPECTATION_FAILED;
			retObj = BAD_RECAPTCHA + captchaVerifyMessage;	
		} else {
			
	    	try {  	
	    	// Store user's password in Persistanse Layer
	    		MySecretDataDTO  mySecretData = new MySecretDataDTO(email, password, secureToken);
	    		mySecretData.setNewInstance(true);
	    		mySecretData.setIP(ip);
				persistance.writeSecrets(email, null,  mySecretData);
				retObj = secureToken;
			} catch (NoSuchAlgorithmException  | AccessException e) {
				e.printStackTrace();
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				retObj = e.getMessage();	
			}	
		}	
        return new ResponseEntity<Object>(retObj, status);
    }
	
}
