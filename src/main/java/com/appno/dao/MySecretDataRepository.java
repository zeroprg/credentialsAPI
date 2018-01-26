package com.appno.dao;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.appno.persistance.entities.MySecretData;


@Service
public interface MySecretDataRepository extends CrudRepository<MySecretData, Long> {

	List<MySecretData> findByEmail(String email);
    List<MySecretData> findBySecureToken(String secureToken);
    
    @Query("select c from MySecretData c where c.email = :email")
    Stream<MySecretData> findByEmailReturnStream(@Param("email") String email);

    List<MySecretData> findByDate(Date date);
    
    

    //@Query("select c from MySecretData c")
    //Stream<MySecretData> findAllAndStream();

    //List<MySecretData> findByDateBetween(Date from, Date to);

}
