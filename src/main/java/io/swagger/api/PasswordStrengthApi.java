package io.swagger.api;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-20T15:40:13.391-08:00")

@Api(value = "password-strength", description = "the password-strength API")
public interface PasswordStrengthApi {

    @ApiOperation(value = "PasswordStrength", notes = "By tapping on the \"Password\" field at the main page, an AJAX request is sent to this endpoint to check how strong the password is. Result is itnteger from 0 to 100 /. Password send in Base64 encoding", response = Object.class, tags={ "password", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The server successfully processed the request and is not returning any content.", response = Object.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Object.class),
        @ApiResponse(code = 500, message = "Internal server exception", response = Object.class),
        @ApiResponse(code = 401, message = "The user does not have the necessary credentials.", response = Object.class) })
    @RequestMapping(value = "/password-strength",
        method = RequestMethod.GET)
    ResponseEntity<Object> passwordStrengthGet(@ApiParam(value = "How strong passord is" ,required=true ) @RequestHeader(value="password", required=true) String password);

}
