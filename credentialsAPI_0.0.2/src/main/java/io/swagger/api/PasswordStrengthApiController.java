package io.swagger.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;

import com.appno.services.IPasswordsTool;

import io.swagger.annotations.ApiParam;
import io.swagger.model.PasswordStrength;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-20T11:12:24.482-08:00")

@Controller
public class PasswordStrengthApiController implements PasswordStrengthApi {
    @Autowired
	private IPasswordsTool passwordTool;  
 
    public ResponseEntity<Object> passwordStrengthGet(@ApiParam(value = "How strong password is?" ,required=true ) @RequestHeader(value="password", required=true) String password) {
        // do some magic!
    	PasswordStrength ps = new PasswordStrength(passwordTool.strengh(password));
        return new ResponseEntity<Object>(ps, HttpStatus.OK);
    }

}
