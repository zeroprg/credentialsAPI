package io.swagger.api;

import java.security.NoSuchAlgorithmException;

import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;

import com.appno.dto.model.MySecretDataDTO;
import com.appno.services.IPasswordsTool;
import com.appno.services.IPersistence;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Errormsg;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-24T21:44:51.228-08:00")

@Controller
public class ResetPasswordApiController implements ResetPasswordApi {
    private static final Object RESET_PASSWORD_MSG = "eMail to reset password was sent to ";
	@Autowired
    private IPersistence persistance;
	@Autowired
	private IPasswordsTool passwordTool;

    public ResponseEntity<Object> resetPasswordPost(@ApiParam(value = "" ,required=true ) @RequestHeader(value="securetoken", required=true) String securetoken) {
    	HttpStatus status = HttpStatus.OK;
		Object retObj = securetoken;
		
		MySecretDataDTO mySecretData = null;
		try {
			mySecretData = (MySecretDataDTO) persistance.readSecretsByToken(securetoken);

			if (mySecretData == null) {
				Errormsg error = new Errormsg();
				error.setCode(HttpStatus.UNAUTHORIZED.value());
				error.setMsg(HttpStatus.UNAUTHORIZED.getReasonPhrase());
				retObj = error;
				status = HttpStatus.UNAUTHORIZED;
			} else {
				// initialize eMail to customer
				String eMail = mySecretData.getEmail();
				status = HttpStatus.OK;
				// generate secure token which will be use to authenticate by
				// default in browser
				String newSecureToken = passwordTool.generatePassPhrase();
				retObj = RESET_PASSWORD_MSG + eMail + " with link  ... /validate-by-email/" + newSecureToken
						+ "(This is the test only)";
				MySecretDataDTO mySecretDataDTO = new MySecretDataDTO(null, null, newSecureToken);
				// make temporary not active
				mySecretDataDTO.setActive(false);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			retObj = status.getReasonPhrase();			
		} catch (AccessException e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			retObj = e.getMessage();			
		}
    	
        return new ResponseEntity<Object>(retObj, status );
    }

}
