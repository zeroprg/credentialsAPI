package io.swagger.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;

import com.appno.services.impl.RecaptchaService;

import io.swagger.annotations.ApiParam;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-20T15:40:13.391-08:00")

@Controller
public class RecaptchaApiController implements RecaptchaApi {

	private static final Object BAD_RECAPTCHA = "You identified as robot: ";
	@Autowired 
	RecaptchaService captchaService;

	public ResponseEntity<Object> registerPost(
    		@ApiParam(value = "" ,required=true ) @RequestHeader(name="g-recaptcha-response") String recaptchaResponse,
    		@ApiParam(value = "" ,required=true ) @RequestHeader(name="ip-address") String ip) 
	{
    	
 
		HttpStatus status = HttpStatus.OK;
		Object retObj = status.getReasonPhrase();

		String captchaVerifyMessage = 
				captchaService.verifyRecaptcha(ip, recaptchaResponse);
			
		if ( captchaVerifyMessage != null) {
			status = HttpStatus.EXPECTATION_FAILED;
			retObj = BAD_RECAPTCHA + captchaVerifyMessage;	
		} 
	   return new ResponseEntity<Object>(retObj, status);
    }
	
}
