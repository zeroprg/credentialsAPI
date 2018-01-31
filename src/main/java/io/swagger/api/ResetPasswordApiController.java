package io.swagger.api;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;

import com.appno.configuration.LocalServerUrlBuilder;
import com.appno.dto.model.MySecretDataDTO;
import com.appno.email.EmailService;
import com.appno.services.IPasswordsTool;
import com.appno.services.IPersistence;

import io.swagger.annotations.ApiParam;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-24T21:44:51.228-08:00")

@Controller
public class ResetPasswordApiController implements ResetPasswordApi {
    private static final Object RESET_PASSWORD_MSG = "eMail to reset password was sent to ";
	private static final String EMAIL_TEXT = "Your password was reset , please confirm it by clicking on this url: ";
	private static final String EMAIL_HEADER = "BCAA password reset";

	@Autowired    
	LocalServerUrlBuilder localServerUrlBuilder;
    @Autowired
    private EmailService emailService;
    
	@Autowired
    private IPersistence persistance;
	@Autowired
	private IPasswordsTool passwordTool;

    public ResponseEntity<Object> resetPasswordPost(@ApiParam(value = "" ,required=true ) @RequestHeader(value="securetoken", required=true) String secureToken) {
    	HttpStatus status = HttpStatus.OK;
		Object retObj = null;
		boolean isEmailToken = false;
		MySecretDataDTO mySecretDataDTO = null;
		try {
			mySecretDataDTO = (MySecretDataDTO) persistance.readSecretsByToken(secureToken, isEmailToken);

			if (mySecretDataDTO == null) {
				status = HttpStatus.UNAUTHORIZED;
				retObj = status.getReasonPhrase();	
				
			} else {
			
				String eMail = mySecretDataDTO.getEmail();
				status = HttpStatus.OK;
				// generate secure token which will be use to authenticate by
				// default in browser
				String newSecureToken = passwordTool.generatePassPhrase();
				// initialize eMail to customer
				String link = localServerUrlBuilder.getUrl() + "/validate-by-email?securetoken=" + newSecureToken;
				emailService.sendSimpleMessage(eMail, EMAIL_HEADER, EMAIL_TEXT + link );
				retObj = RESET_PASSWORD_MSG + eMail + " with link: " + link;
				
				mySecretDataDTO = new MySecretDataDTO(null, null, newSecureToken);
				// make temporary not active : do not allow to signin by eMail validation token
				mySecretDataDTO.setActive(false);
				persistance.writeSecrets(null, secureToken, mySecretDataDTO);
			}
		} catch (NoSuchAlgorithmException  | AccessException e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			retObj = e.getMessage();	
		}
    	
        return new ResponseEntity<Object>(retObj, status );
    }

}
