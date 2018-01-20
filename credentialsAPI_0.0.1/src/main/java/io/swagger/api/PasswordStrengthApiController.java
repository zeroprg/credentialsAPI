package io.swagger.api;


import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiParam;
import io.swagger.model.PasswordStrength;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-20T11:12:24.482-08:00")

@Controller
public class PasswordStrengthApiController implements PasswordStrengthApi {



    public ResponseEntity<Object> passwordStrengthGet(@ApiParam(value = "How strong passord is" ,required=true ) @RequestHeader(value="password", required=true) String password) {
        // do some magic!
    	PasswordStrength ps = new PasswordStrength(8);
        return new ResponseEntity<Object>(ps, HttpStatus.OK);
    }

}
