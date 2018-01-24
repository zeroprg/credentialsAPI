package com.appno.services.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.appno.dao.MySecretDataRepository;
import com.appno.dto.model.MySecretDataDTO;
import com.appno.persistance.entities.MySecretData;
import com.appno.services.IPersistence;

//for jsr310 java 8 java.time.*
//@EntityScan(
//        basePackageClasses = { SpringBootConsoleApplication.class, Jsr310JpaConverters.class }
//)
@Component
public class DBPersistanceLayer implements IPersistence {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    DataSource dataSource;

    @Autowired
    MySecretDataRepository mySecretDataRepository;


	@Override
    @Transactional
	public void writeSecrets(String userId, Object secureObject) {
		MySecretDataDTO mySecretDataDTO = (MySecretDataDTO)secureObject;
		MySecretData mySecretData =  new MySecretData();
		mySecretData.setDate(new Date());
		mySecretData.setEmail(mySecretDataDTO.getEMail());
		mySecretData.setPassword(mySecretDataDTO.getPassword());
		mySecretData.setSecureToken(mySecretDataDTO.getSecureToken());
		mySecretDataRepository.save(mySecretData);
	}


	@Override
	public Object readSecretsById(String email) {
		List<MySecretData> mySecretDatas = mySecretDataRepository.findByEmail(email);
		MySecretData mySecretData = mySecretDatas.get(0);
		MySecretDataDTO mySecretDataDTO =  new MySecretDataDTO();
		mySecretDataDTO.setEMail(mySecretData.getEmail());
		mySecretDataDTO.setPassword(mySecretData.getPassword());
		mySecretDataDTO.setSecureToken(mySecretData.getSecureToken());
		
		return mySecretDataDTO;
	}


	@Override
	public Object readSecretsByToken(String secureToken) {
		List<MySecretData> mySecretDatas = mySecretDataRepository.findBySecureToken(secureToken);
		return mySecretDatas.get(0);
	}

}