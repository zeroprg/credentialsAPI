package io.swagger.api;

import io.swagger.model.Errormsg;

import io.swagger.annotations.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.appno.dto.model.MySecretDataDTO;
import com.appno.services.IPasswordsTool;
import com.appno.services.IPersistence;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-25T15:16:33.343-08:00")

@Controller
public class ValidateByEmailApiController implements ValidateByEmailApi {

    @Autowired
	private IPasswordsTool passwordTool; 
    @Autowired
    private IPersistence persistance;


    public ResponseEntity<Object> validateByEmailGet(@ApiParam(value = "" ,required=true ) @RequestHeader(value="securetoken", required=true) String securetoken) {
    	HttpStatus status = HttpStatus.OK;
		Object retObj = securetoken;
		boolean isEmailToken = true;
		
    	// Read system by eMail validation token (this token can be used to sign in only for password change)
    	try {
			if( persistance.readSecretsByToken(securetoken, isEmailToken) == null ) {
				status = HttpStatus.UNAUTHORIZED;
				retObj = status.getReasonPhrase();
			} else {
				MySecretDataDTO mySecretDataDTO =  new MySecretDataDTO(null, null, securetoken); 
				// make active back again
				mySecretDataDTO.setActive(true);
				// generate secure token which will be use to authenticate by default in browser
				String newSecureToken = passwordTool.generatePassPhrase();
				mySecretDataDTO.setSecureToken(newSecureToken);
				// update secure token
				persistance.writeSecrets(null, securetoken, mySecretDataDTO);    		
				retObj = newSecureToken;
				status = HttpStatus.OK;
			}
		} catch (NoSuchAlgorithmException  | AccessException e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			retObj = e.getMessage();	
		}
    	
        return new ResponseEntity<Object>(retObj, status );
    }

}
