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
	@Autowired
	private IPersistence persistance;
	@Autowired
	private IPasswordsTool passwordTool;

	public ResponseEntity<Object> sendValidationEmailPost(
			@ApiParam(value = "", required = true) @RequestHeader(value = "email", required = true) String email) {
		HttpStatus status = HttpStatus.OK;
		Object retObj = email;
		try {

			MySecretDataDTO mySecretData = (MySecretDataDTO) persistance.readSecretsById(email);

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
				retObj = RESET_PASSWORD_MSG + email + " with link  ... /validate-by-email/" + newSecureToken
						+ "(This is the test only)";

				MySecretDataDTO mySecretDataDTO = new MySecretDataDTO(email, null, newSecureToken);
				// make temporary not active
				mySecretDataDTO.setActive(false);
				// send eMail here ....
				persistance.writeSecrets(email, null, mySecretDataDTO);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			retObj = e.getMessage();
		} catch (AccessException e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			retObj = e.getMessage();
		}

		return new ResponseEntity<Object>(retObj, status);
	}

}
