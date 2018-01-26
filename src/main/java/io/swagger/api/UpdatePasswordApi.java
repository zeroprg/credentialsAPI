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

@Api(value = "update-password", description = "the update-password API")
public interface UpdatePasswordApi {

    @ApiOperation(value = "UpdatePassword", notes = "By tapping on the \"Update\" button at the main page, an AJAX request is sent to this endpoint with Base64 new password and \"userId:password\" Bae64 encoded pair . Ideally redirected to /.", response = Object.class, tags={ "update", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The server successfully processed the request.", response = Object.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Object.class),
        @ApiResponse(code = 401, message = "The user does not have the necessary credentials.", response = Object.class) })
    @RequestMapping(value = "/update-password",
        method = RequestMethod.POST)
    ResponseEntity<Object> updatePasswordPost(@ApiParam(value = "" ,required=true ) @RequestHeader(value="Authorization", required=true) String authorization,
        @ApiParam(value = "" ,required=true ) @RequestHeader(value="newpassword", required=true) String newpassword);

}
