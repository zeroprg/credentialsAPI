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

@Api(value = "signin", description = "the signin API")
public interface SigninApi {

    @ApiOperation(value = "Signin", notes = "Secured token which stored by browser, used to authenticate in default mode. By filling in the form and pressing the \"Sign In\" button at the sign-in page (/signin), an AJAX GET is sent to this endpoint. You will then be redirected to the main page (/main) if the request is successful or shown an error message if it fails.", response = Void.class, tags={ "signin", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The server successfully processed the request.", response = Void.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Void.class),
        @ApiResponse(code = 401, message = "The user does not have the necessary credentials.", response = Void.class) })
    @RequestMapping(value = "/signin",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<Void> signinGet( @NotNull @Size(min=8) @ApiParam(value = "uuid used to register in system", required = true) @RequestParam(value = "uuid", required = true) String uuid,
        @ApiParam(value = "" ,required=true ) @RequestHeader(value="securetoken", required=true) String securetoken);

}
