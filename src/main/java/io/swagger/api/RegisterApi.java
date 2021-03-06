package io.swagger.api;


import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-20T15:40:13.391-08:00")

@Api(value = "register", description = "the register API")
public interface RegisterApi {

	   @ApiOperation(value = "Register", notes = "By filling the form and pressing a \"Regiter\" button at the top page (/), the form will send an AJAX POST to this endpoint. You will be then redirected to the main page (/main) if the request is successful or will show an error message if it fails.Output is secure token which will be use to authenticate by default in browser", response = Object.class, tags={ "register", })
	    @ApiResponses(value = { 
	        @ApiResponse(code = 200, message = "The server successfully processed the request. Return secureToken in body as plain text", response = Object.class),
	        @ApiResponse(code = 400, message = "Bad Request", response = Object.class),
	        @ApiResponse(code = 500, message = "Internal server exception", response = Object.class),
	        @ApiResponse(code = 401, message = "The user does not have the necessary credentials.", response = Object.class) })
	    @RequestMapping(value = "/register",
	            produces = { "text/plain" }, 
	            consumes = { "application/json" },
	        method = RequestMethod.POST)
	    ResponseEntity<Object> registerPost(@ApiParam(value = "" ,required=true ) @RequestHeader(value="Authorization", required=true) String authorization);

}
