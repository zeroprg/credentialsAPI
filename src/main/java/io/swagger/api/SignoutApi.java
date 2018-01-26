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

@Api(value = "signout", description = "the signout API")
public interface SignoutApi {

    @ApiOperation(value = "Signout", notes = "Secured token which stored by browser, used to authenticate in default mode. By tapping on the \"Sign Out\" button at the main page, an AJAX request is sent to this endpoint, sign out happened, then redirected to /.", response = Void.class, tags={ "signout", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The server successfully processed the request.", response = Void.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Void.class),
        @ApiResponse(code = 401, message = "The user does not have the necessary credentials.", response = Void.class) })
    @RequestMapping(value = "/signout",
    			method = RequestMethod.GET)
    ResponseEntity<Void> signoutGet(@ApiParam(value = "" ,required=true ) @RequestHeader(value="securetoken", required=true) String securetoken);

}
