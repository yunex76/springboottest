package com.kyoborealco.kreps.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;

/**
 * <pre> 
 * 사용자 통합로그인 entity
 * 관리자, 입주사 (Users), 채용지원자(RecruitMain), 협력업체(CooperCompany)
 * </pre> 
 * 
 */
@Data
@Entity 
@Table(name="Users", schema="dbo")
public class Users implements Serializable {

	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** 사용자ID */
	@Id
	@Column(name="UserID") 
	private String userId;
	
	/** 사용자명 */
	@Column(name="UserNm") 
	private String userName;
	
	/** 패스워드 */
	@Column(name="Password") 
	private String password;

	/** 작성자ID */ 
	@Column(name="InsertID") 
	private String created; 

	/** 작성일시 */ 
	@Column(name="InsertDT") 
	private Date createdAt; 

	/** 수정자ID */ 
	@Column(name="UpdateID") 
	private String modified; 

	/** 수정일시 */ 
	@Column(name="UpdateDT") 
	private Date modifiedAt; 

	/** 버전 */ 
	@Version 
	@Column(name="Version")
	private Integer version = 1; 
	
	
}
