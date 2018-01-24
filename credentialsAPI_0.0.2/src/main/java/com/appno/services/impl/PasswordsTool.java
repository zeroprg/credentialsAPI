package com.appno.services.impl;

import me.gosimple.nbvcxz.scoring.Result;

import org.springframework.stereotype.Component;

import com.appno.services.IPasswordsTool;

import me.gosimple.nbvcxz.Nbvcxz;
import me.gosimple.nbvcxz.resources.Generator;
import me.gosimple.nbvcxz.resources.Generator.CharacterTypes;

/**
 * 
 */

/**
 * @author Vitaly
 *
 */
@Component("passwordStrength")
public class PasswordsTool implements IPasswordsTool {

	// With all defaults...
	private Nbvcxz nbvcxz = new Nbvcxz();
	
	/* (non-Javadoc)
	 * @see com.appno.services.PasswordStrength#strengh(java.lang.String)
	 */
	@Override
	public Integer strengh(String password) {
		// Estimate password 
		Result result = nbvcxz.estimate(password);
		return result.getEntropy().intValue();
	}

	@Override
	public String generatePassPhrase() {
		String pass1 = Generator.generateRandomPassword(CharacterTypes.ALPHANUMERIC, 36) ;
		return pass1;
	}

}
