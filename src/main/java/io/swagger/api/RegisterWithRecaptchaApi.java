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

@Api(value = "register-with-recaptcha", description = "the register-with-recaptcha API")
public interface RegisterWithRecaptchaApi {

	   @ApiOperation(value = "RegisterWithRecaptcha", notes = "The same as /register end point except support Gogle recaptcha functionality: https://o7planning.org/en/10397/using-google-recaptcha-with-java-web-application", response = Object.class, tags={ "register-with-recaptcha", })
	    @ApiResponses(value = { 
	        @ApiResponse(code = 200, message = "The server successfully processed the request. Return secureToken in body as plain text", response = Object.class),
	        @ApiResponse(code = 400, message = "Bad Request", response = Object.class),
	        @ApiResponse(code = 417, message = "Expecting captcha or your identified as robot", response = Object.class),

	        @ApiResponse(code = 500, message = "Internal server exception", response = Object.class),
	        @ApiResponse(code = 401, message = "The user does not have the necessary credentials.", response = Object.class) })
	    @RequestMapping(value = "/register-with-recaptcha",
	            produces = { "text/plain" }, 
	            consumes = { "application/json" },
	        method = RequestMethod.POST)
	    ResponseEntity<Object> registerPost(@ApiParam(value = "" ,required=true ) @RequestHeader(value="Authorization", required=true) String authorization,
	    		@ApiParam(value = "" ,required=true ) @RequestHeader(name="g-recaptcha-response") String recaptchaResponse,
	    		@ApiParam(value = "" ,required=true ) @RequestHeader(name="ip-address") String ip
	    		);

}
