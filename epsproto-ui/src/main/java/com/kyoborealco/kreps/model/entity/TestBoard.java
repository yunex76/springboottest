package com.kyoborealco.kreps.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/** 
 * <pre> 
 * dbo - test board 
 * </pre> 
 * 
 * @author 	: nam yunhyeok 
 * @date 	: 2020. 5. 18
 * @version : 
 */ 
@Data 
@Entity 
@Table(name="test_board", schema="dbo") 
public class TestBoard implements Serializable {
	
	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = -1L;

	/** board_id */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="board_id") 
	private Integer boardId; 

	/** name */ 
	@Column(name="name") 
	private String name; 

	/** position */ 
	@Column(name="position") 
	private String position; 

	/** office */ 
	@Column(name="office") 
	private String office; 

	/** age */ 
	@Column(name="age") 
	private Integer age; 

	/** start_date */ 
	@Column(name="start_date") 
	private String startDate; 
	
	/** salary */ 
	@Column(name="salary") 
	private Integer salary; 
}
