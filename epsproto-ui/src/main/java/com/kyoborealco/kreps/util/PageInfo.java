package com.kyoborealco.kreps.util;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageInfo {

	private Integer pageSize;		   // 페이지당 row 수
	private Integer totalCount;        // 게시판 전체 데이터 개수
	private List<Integer> pageNumbers; // 게시판 화면에서 한번에 보여질 페이지 번호의 개수 ( 1,2,3,4,5)
	
	private Integer startPage;         // 현재 화면에서 보이는 startPage 번호
	private Integer endPage;           // 현재 화면에 보이는 endPage 번호
	private Boolean isPrev;            // 페이징 이전 버튼 활성화 여부
	private Boolean isNext;            // 페이징 다음 버튼 활서화 여부
}
