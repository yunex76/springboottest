package me.whiteshop.accounts;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Account {

	@Id @GeneratedValue
	private Long id;
	
	@Column(unique = true)
	private String username;
	
	private String password;
	
	private String email;
	
	private String fullName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date joined;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
	
	
}
