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

import com.appno.configuration.LocalServerUrlBuilder;
import com.appno.dto.model.MySecretDataDTO;
import com.appno.email.EmailService;
import com.appno.persistance.entities.MySecretData;
import com.appno.services.IPasswordsTool;
import com.appno.services.IPersistence;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-24T21:44:51.228-08:00")

@Controller
public class SendValidationEmailApiController implements SendValidationEmailApi {
    private static final Object RESET_PASSWORD_MSG = "eMail to reset password was sent to ";
	private static final String EMAIL_TEXT = "Your password was reset , please confirm it by clicking on this url: ";
	private static final String EMAIL_HEADER = "BCAA password reset";
	
	@Autowired    
	private LocalServerUrlBuilder localServerUrlBuilder;
    @Autowired
    private EmailService emailService;
    
	@Autowired
	private IPersistence persistance;
	@Autowired
	private IPasswordsTool passwordTool;

	public ResponseEntity<Object> sendValidationEmailPost(
			@ApiParam(value = "", required = true) @RequestHeader(value = "email", required = true) String eMail) {
		HttpStatus status = HttpStatus.OK;
		Object retObj = eMail;
		try {

			MySecretDataDTO mySecretData = (MySecretDataDTO) persistance.readSecretsById(eMail);

			if (mySecretData == null) {
				Errormsg error = new Errormsg();
				error.setCode(HttpStatus.NO_CONTENT.value());
				error.setMsg(HttpStatus.NO_CONTENT.getReasonPhrase());
				retObj = error;
				status = HttpStatus.NO_CONTENT;
			} else {
				// initialize sendig eMail to customer
				status = HttpStatus.OK;
				// generate secure token which will be use to authenticate by
				// default in browser
				String newSecureToken = passwordTool.generatePassPhrase();
				// initialize eMail to customer
				String link = localServerUrlBuilder.getUrl() + "/validate-by-email?securetoken=" + newSecureToken;
				emailService.sendSimpleMessage(eMail, EMAIL_HEADER, EMAIL_TEXT + link );
				retObj = RESET_PASSWORD_MSG + eMail + " with link: " + link;


				MySecretDataDTO mySecretDataDTO = new MySecretDataDTO(eMail, null, newSecureToken);
				// make temporary not active
				mySecretDataDTO.setActive(false);
				// send eMail here ....
				persistance.writeSecrets(eMail, null, mySecretDataDTO);
			}
		} catch (NoSuchAlgorithmException  | AccessException e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			retObj = e.getMessage();	
		}

		return new ResponseEntity<Object>(retObj, status);
	}

}
