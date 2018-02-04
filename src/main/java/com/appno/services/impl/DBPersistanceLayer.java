package com.appno.services.impl;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.appno.dao.MySecretDataRepository;
import com.appno.dto.model.MySecretDataDTO;
import com.appno.persistance.entities.MySecretData;
import com.appno.security.ISHA;
import com.appno.services.IPersistence;


@Component
public class DBPersistanceLayer implements IPersistence {

 //   private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private static final String DUPLICATED_USER = "User already exist in system.";

	private static final String MORE_THEN_ONE_USER_FOUND = "More then one user found";

	private static final String TOKEN_EXPIRED = "Token expired, please relogin";

	private static final String USER_DISABLED = "User temporaly disabled";

	private static final String USER_UNREGISTERED = "User was permanent disabled";

	private static final String USER_NOT_EXIST_OR_DISABLED = "User does not exist or disabled";

    @Autowired 
    ISHA sha;
    
    @Autowired
    DataSource dataSource;

    @Autowired
    MySecretDataRepository mySecretDataRepository;


	@Override
    @Transactional
	public void writeSecrets(String userId, String secureToken, Object secureDTO) throws NoSuchAlgorithmException, AccessException {
		MySecretDataDTO mySecretDataDTO = (MySecretDataDTO)secureDTO;
		List<MySecretData> mySecretDatas = null;
		MySecretData mySecretData;
		
		//Search if  it exist ?
		if( userId != null) 
			mySecretDatas = mySecretDataRepository.findByEmail(userId);
		else if( secureToken != null) 
			mySecretDatas = mySecretDataRepository.findBySecureToken(sha.hash(secureToken));
		
		if (mySecretDatas == null || mySecretDatas.isEmpty())
			mySecretData = new MySecretData();
		else {
			mySecretData = mySecretDatas.get(0);
			if( mySecretDataDTO.getNewInstance() ) throw new AccessException(DUPLICATED_USER);
		}	
		mySecretData.setDate(new Date());
		long now = System.currentTimeMillis();
		// set validity period for 900 minutes
		now += (mySecretDataDTO.getValidInMinutes() == null ? 900 * 60 * 1000 :mySecretDataDTO.getValidInMinutes() * 60 * 1000);
		mySecretData.setExpireDate(new Date(now));
		
	    mySecretData.setActive(mySecretDataDTO.getActive());

		if( mySecretDataDTO.getEmail() != null )  mySecretData.setEmail(mySecretDataDTO.getEmail());
		if( mySecretDataDTO.getPassword() != null ) mySecretData.setPassword(sha.hash(mySecretDataDTO.getPassword()));
		if( mySecretDataDTO.getSecureToken() != null )  mySecretData.setSecureToken(sha.hash(mySecretDataDTO.getSecureToken()));
		mySecretData.setDisabled(mySecretDataDTO.getDisabled());
		mySecretData.setIP(mySecretDataDTO.getIP());
		mySecretDataRepository.save(mySecretData);
	}


	@Override
	public Object readSecretsById(String email) throws AccessException {
		List<MySecretData> mySecretDatas = mySecretDataRepository.findByEmail(email);
		MySecretDataDTO mySecretDataDTO = null;
		if(!mySecretDatas.isEmpty()){
			checkAccessException(mySecretDatas, false);
			MySecretData mySecretData = mySecretDatas.get(0);
			mySecretDataDTO =  new MySecretDataDTO();
			mySecretDataDTO.setEmail(mySecretData.getEmail());
			mySecretDataDTO.setPassword(mySecretData.getPassword());
			mySecretDataDTO.setSecureToken(mySecretData.getSecureToken());
			mySecretDataDTO.setDisabled(mySecretData.getDisabled());
		}else throw new AccessException(USER_NOT_EXIST_OR_DISABLED);
		return mySecretDataDTO;
	}


	private void checkAccessException(List<MySecretData>mySecretDatas, boolean checkTokenValidation) throws AccessException{
		
		if(mySecretDatas.size() > 1) {
			throw new AccessException(MORE_THEN_ONE_USER_FOUND);
		} else 
		if(mySecretDatas.get(0).getDisabled()) {
			throw new AccessException(USER_UNREGISTERED);
		} else 
		// do the check if registration was by token	
		//TODO remove it if you decide use temporary time based tokens	
		/*		
 	    if((new Date()).compareTo(mySecretDatas.get(0).getExpireDate()) > 0  && checkTokenValidation) {
			throw new AccessException(TOKEN_EXPIRED);
		}
		*/
		// do the check if registration was by token
		if( !mySecretDatas.get(0).getActive() && checkTokenValidation) {
			throw new AccessException(USER_DISABLED);
		}
	
	}


	@Override
	public Object readSecretsByToken(String secureToken, boolean isEmailToken) throws NoSuchAlgorithmException, AccessException {
		List<MySecretData> mySecretDatas = null;
		mySecretDatas = mySecretDataRepository.findBySecureToken(sha.hash(secureToken));
		MySecretDataDTO mySecretDataDTO = null;
		if(!mySecretDatas.isEmpty()){
			checkAccessException(mySecretDatas, !isEmailToken);
			MySecretData mySecretData = mySecretDatas.get(0);
			mySecretDataDTO =  new MySecretDataDTO();
			mySecretDataDTO.setEmail(mySecretData.getEmail());
			mySecretDataDTO.setPassword(mySecretData.getPassword());
			mySecretDataDTO.setSecureToken(mySecretData.getSecureToken());
			mySecretDataDTO.setDisabled(mySecretData.getDisabled());
		} else  throw new AccessException(USER_NOT_EXIST_OR_DISABLED);
		return mySecretDataDTO;
	}

}