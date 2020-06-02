package com.kyoborealco.kreps.zsite.criteria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kyoborealco.kreps.util.PageInfo;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseCriteria{
	
	// page
	private Integer currentPage;
	private PageInfo pageInfo;

}
