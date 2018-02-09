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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-24T21:44:51.228-08:00")

@Api(value = "send-validation-email", description = "the reset-password API")
public interface SendValidationEmailApi {

    @ApiOperation(value = "SendValidationEmail", notes = "Initiate password reset flow initiated by user because user forgot password.  (eMail passed in header).It will send eMail validation  which will have link  to update password endpoint.",
    		response = Object.class, tags={ "reset", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The server successfully processed the request.", response = Object.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Object.class),
        @ApiResponse(code = 500, message = "Internal server exception", response = Object.class),
        @ApiResponse(code = 401, message = "The user does not have the necessary credentials.", response = Object.class) })
    @RequestMapping(value = "/send-validation-email",
        method = RequestMethod.POST)
    ResponseEntity<Object> sendValidationEmailPost(@ApiParam(value = "" ,required=true ) @RequestHeader(value="securetoken", required=true) String email);

}
