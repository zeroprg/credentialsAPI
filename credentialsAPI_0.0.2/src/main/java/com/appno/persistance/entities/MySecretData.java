package com.appno.persistance.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class MySecretData {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    Long id;

    String password;
    String email;

    //@Temporal(TemporalType.DATE)
    @Column(name = "CREATED_DATE")
    Date date;

    String secureToken;
    
    public MySecretData(String password, String email, String secureToken, Date date) {
        this.password = password;
        this.email = email;
        this.date = date;
        this.secureToken = secureToken;
    }

    public MySecretData() {
    }

    @Override
    public String toString() {
        return "MySecretData{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", date=" + date + '\'' +
                ", securedToken= " + secureToken +  
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSecureToken() {
		return secureToken;
	}

	public void setSecureToken(String securedToken) {
		this.secureToken = securedToken;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
