package io.swagger.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;

import com.appno.services.IPersistence;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Errormsg;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-20T15:40:13.391-08:00")

@Controller
public class SigninApiController implements SigninApi {
    @Autowired
    private IPersistence persistance;


    public ResponseEntity<Object> signinGet( 
        @ApiParam(value = "" ,required=true )
        @RequestHeader(value="securetoken", required=true) String securetoken) {

    	HttpStatus status = HttpStatus.OK;
		Object retObj = securetoken;
		
    	
    	if(  persistance.readSecretsByToken(securetoken) == null ) {
    		Errormsg error = new Errormsg();
			error.setCode(HttpStatus.UNAUTHORIZED.value());
			error.setMsg(HttpStatus.UNAUTHORIZED.getReasonPhrase());
			retObj = error;
			status = HttpStatus.UNAUTHORIZED;
    	}
    	
        return new ResponseEntity<Object>(retObj, status );
    }

}
