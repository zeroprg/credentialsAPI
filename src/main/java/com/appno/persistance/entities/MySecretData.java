package com.appno.persistance.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "TOKENS",

indexes =  {@Index(name = "my_index_name",  columnList="secureToken", unique = true),
           @Index(name = "my_index_name2", columnList="email",     unique = false)})
public class MySecretData {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    Long id;

    String password;
    @Column(unique=true)
    String email;

    //@Temporal(TemporalType.DATE)
    @Column(name = "CREATED_DATE")
    Date date;

	Date expireDate;
    Boolean active;
    Boolean disabled;
 //   Boolean admin;
    
    @Column(unique=true)    
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

    
    public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

/*	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
 */   
}
