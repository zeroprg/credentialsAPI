package io.swagger.api;


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

import com.appno.services.IPasswordsTool;

import java.util.List;

import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-20T15:40:13.391-08:00")

@Controller
public class RegisterApiController implements RegisterApi {

    @Autowired
	private IPasswordsTool passwordTool;  

    public ResponseEntity<Object> registerPost( @NotNull @Size(min=8) @ApiParam(value = "This is user name", required = true) @RequestParam(value = "username", required = true) String username,
         @NotNull @ApiParam(value = "This alfa-numeric password", required = true) @RequestParam(value = "password", required = true) String password) {
        // do some magic!
    	String secureToken = passwordTool.generatePassPhrase();
        return new ResponseEntity<Object>(secureToken, HttpStatus.OK);
    }

}
