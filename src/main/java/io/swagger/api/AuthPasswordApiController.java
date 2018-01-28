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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-20T15:40:13.391-08:00")

@Controller
public class AuthPasswordApiController implements AuthPasswordApi {
	@Autowired
	private IPasswordsTool passwordTool;

	@Autowired
	ISHA sha;

	@Autowired
	private IPersistence persistance;

	public ResponseEntity<Object> authPasswordPost(
			@ApiParam(value = "Send encoded user:password pair", required = true) @RequestHeader(value = "Authorization", required = true) String authorization) {

		String credentials[] = authorization.split(":");
		HttpStatus status = null;
		Object retObj = null;

		try {
			MySecretDataDTO mySecretDataDTO = ((MySecretDataDTO) persistance.readSecretsById(credentials[0]));

			// user dosn't exist in system
			if (mySecretDataDTO == null) {
				status = HttpStatus.NO_CONTENT;
				retObj = status.getReasonPhrase();
			} else {
				// user exist in system

				if (mySecretDataDTO.getPassword().equals(sha.hash(credentials[1]))) {
					status = HttpStatus.OK;
					// generate secure token which will be use to authenticate
					// by default in browser
					String newSecureToken = passwordTool.generatePassPhrase();
					retObj = newSecureToken;
					persistance.writeSecrets(credentials[0], null,
							new MySecretDataDTO(credentials[0], null, newSecureToken));
				} else {
					status = HttpStatus.UNAUTHORIZED;
					retObj = status.getReasonPhrase();
				}
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
