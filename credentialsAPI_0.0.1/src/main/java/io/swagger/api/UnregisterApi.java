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

@Api(value = "unregister", description = "the unregister API")
public interface UnregisterApi {

    @ApiOperation(value = "Unregister", notes = "By tapping on the \"Unregister\" button at the main page, an AJAX request is sent to this endpoint, unregister, then redirected to /.", response = Void.class, tags={ "unregister", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The server successfully processed the request.", response = Void.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Void.class),
        @ApiResponse(code = 401, message = "The user does not have the necessary credentials.", response = Void.class) })
    @RequestMapping(value = "/unregister",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<Void> unregisterGet( @NotNull @ApiParam(value = "This is uuid of user which will be uregistered", required = true) @RequestParam(value = "uuid", required = true) String uuid,
        @ApiParam(value = "" ,required=true ) @RequestHeader(value="securetoken", required=true) String securetoken);

}
