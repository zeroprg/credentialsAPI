package io.swagger.api;

import io.swagger.model.Errormsg;

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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-25T15:16:33.343-08:00")

@Api(value = "validate-by-email", description = "the validate-by-email API")
public interface ValidateByEmailApi {

    @ApiOperation(value = "ValidateByEmail", notes = "By clicking on the \"eMail validation\" link in  eMail , an GET request is sent to this endpoint. Validation done by token (this token can not be used to sign in) . Ideally redirected to /update-pasword UI.", response = Object.class, tags={ "validate", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The server successfully processed the request.", response = Object.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Object.class),
        @ApiResponse(code = 401, message = "The user does not have the necessary credentials.", response = Object.class) })
    @RequestMapping(value = "/validate-by-email",
        method = RequestMethod.GET)
    ResponseEntity<Object> validateByEmailGet(@ApiParam(value = "" ,required=true ) @RequestParam(value="securetoken", required=true) String securetoken);

}
