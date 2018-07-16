package com.kyoborealco.mro.mq.dto;

import lombok.Data;

/**
 * 자재정보DTO
 * 
 * @author kiconam
 *
 */
@Data
public class MaterialInfoDTO {

	/**
	 * 자재표준번호
	 */
	private String materialNo;
	
	/**
	 * 자재명
	 */
	private String materialName;
	
	/**
	 * 자재코드
	 */
	private String matCode;
	
	/**
	 * 상위코드
	 */
	private String upperCode;
	
	/**
	 * 자재이미지(base64)
	 */
	private String matImage;
}
