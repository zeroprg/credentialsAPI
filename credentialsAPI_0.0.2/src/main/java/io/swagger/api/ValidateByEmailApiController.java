package io.swagger.api;

import io.swagger.model.Errormsg;

import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-25T15:16:33.343-08:00")

@Controller
public class ValidateByEmailApiController implements ValidateByEmailApi {



    public ResponseEntity<Void> validateByEmailGet(@ApiParam(value = "" ,required=true ) @RequestHeader(value="securetoken", required=true) String securetoken) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
