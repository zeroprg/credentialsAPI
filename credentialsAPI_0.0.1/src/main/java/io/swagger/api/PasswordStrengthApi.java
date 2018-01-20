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

@Api(value = "password-strength", description = "the password-strength API")
public interface PasswordStrengthApi {

    @ApiOperation(value = "PasswordStrength", notes = "By tapping on the \"Password\" field at the main page, an AJAX request is sent to this endpoint to check how strong the password is. Result is itnteger from 0 to 10 /. Password send in Base64 encoding", response = Object.class, tags={ "password", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "The server successfully processed the request and is not returning any content.", response = Object.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Object.class),
        @ApiResponse(code = 401, message = "The user does not have the necessary credentials.", response = Object.class) })
    @RequestMapping(value = "/password-strength",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<Object> passwordStrengthGet(@ApiParam(value = "How strong passord is" ,required=true ) @RequestHeader(value="password", required=true) String password);

}
