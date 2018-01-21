package io.swagger.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;

import com.appno.services.IPasswordsTool;

import io.swagger.annotations.ApiParam;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-20T15:40:13.391-08:00")

@Controller
public class AuthPasswordApiController implements AuthPasswordApi {

    @Autowired
	private IPasswordsTool passwordTool;  

    public ResponseEntity<Object> authPasswordPost(@ApiParam(value = "Send encoded user:password pair" ,required=true ) @RequestHeader(value="Authorization", required=true) String authorization) {
        // do some magic!
    	String secureToken = passwordTool.generatePassPhrase();
        return new ResponseEntity<Object>(secureToken, HttpStatus.OK);
    }

}
