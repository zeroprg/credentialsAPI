package io.swagger.api;


import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;

import com.appno.services.IPersistence;

import io.swagger.annotations.ApiParam;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-20T15:40:13.391-08:00")

@Controller
public class SigninApiController implements SigninApi {
    @Autowired
    private IPersistence persistance;


    public ResponseEntity<Object> signinPost( 
        @ApiParam(value = "" ,required=true )
        @RequestHeader(value="securetoken", required=true) String securetoken) {

    	HttpStatus status = HttpStatus.OK;
		Object retObj = securetoken;
		boolean isEmailToken = false;
    	
    	try {
			if(  persistance.readSecretsByToken(securetoken, isEmailToken) == null ) {
/*				Errormsg error = new Errormsg();
				error.setCode(HttpStatus.UNAUTHORIZED.value());
				error.setMsg(HttpStatus.UNAUTHORIZED.getReasonPhrase());*/
				status = HttpStatus.UNAUTHORIZED;
				retObj = status.getReasonPhrase();

			}
		} catch (NoSuchAlgorithmException  | AccessException e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			retObj = e.getMessage();	
		}
    	
        return new ResponseEntity<Object>(retObj, status );
    }

}
