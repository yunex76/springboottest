package com.kyoborealco.kreps.zsite.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class TestBoardCriteria extends BaseCriteria implements Criteria {

	private String name; 			// 이름
	private String position; 		// 직위
	private String startDateFrom;	// 시작일(From)
	private String startDateTo;		// 시작일(To)
}
