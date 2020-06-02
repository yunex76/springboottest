package com.kyoborealco.kreps.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * <pre> 
 * dbo - test board dto
 * </pre> 
 * 
 * @author 	: nam yunhyeok 
 * @date 	: 2020. 5. 21
 * @version : 
 */ 
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class TestBoardDTO {
	
	/** board_id */
	private Integer boardId; 

	/** name */ 
	private String name; 

	/** position */ 
	private String position; 

	/** office */ 
	private String office; 

	/** age */ 
	private Integer age; 

	/** start_date */ 
	private String startDate; 
	
	/** salary */ 
	private Integer salary;
	
}
