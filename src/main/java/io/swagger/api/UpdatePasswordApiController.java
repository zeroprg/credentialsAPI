package io.swagger.api;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;

import com.appno.dto.model.MySecretDataDTO;
import com.appno.security.ISHA;
import com.appno.services.IPasswordsTool;
import com.appno.services.IPersistence;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Errormsg;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-24T21:44:51.228-08:00")

@Controller
public class UpdatePasswordApiController implements UpdatePasswordApi {
	@Autowired
	ISHA sha;

	@Autowired
	private IPasswordsTool passwordTool;
	@Autowired
	private IPersistence persistance;

	public ResponseEntity<Object> updatePasswordPost(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Authorization", required = true) String authorization,
			@ApiParam(value = "", required = true) @RequestHeader(value = "newpassword", required = true) String newpassword,
			@ApiParam(value = "", required = true) @RequestHeader(value = "securetoken", required = true) String secureToken) {
		
		String credentials[] = authorization.split(":");
		MySecretDataDTO mySecretDataDTO = null;
		HttpStatus status;
		Object retObj;
		boolean isEmailToken = false;
		
		try {
			mySecretDataDTO = ((MySecretDataDTO) persistance.readSecretsByToken(secureToken, isEmailToken));

			String password = mySecretDataDTO.getPassword();
			// String secureToken = null;
			String hashed_password = null;
			hashed_password = sha.hash(credentials[1]);

			if (password.equals(hashed_password) || mySecretDataDTO.getAdmin()) {
				status = HttpStatus.OK;
				retObj = secureToken;
			} else {
				status = HttpStatus.UNAUTHORIZED;
				retObj = status.getReasonPhrase();
			}

			// Update user's password in Persistanse Layer
			mySecretDataDTO.setPassword(newpassword);
			// generate new secure token which will be use to authenticate by
			// default in browser
			String newSecureToken = passwordTool.generatePassPhrase();
			mySecretDataDTO.setSecureToken(newSecureToken);
			retObj = newSecureToken;
			persistance.writeSecrets(credentials[0], null, new MySecretDataDTO(credentials[0], null, newSecureToken));
		} catch (NoSuchAlgorithmException  | AccessException e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			retObj = e.getMessage();	
		}
		
		return new ResponseEntity<Object>(retObj, HttpStatus.OK);
	}

}
