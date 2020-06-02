package com.kyoborealco.kreps.util;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CriteriaHelper {

	/**
	 * 
	 * @param totalCount 전체 조회 건수
	 * @param currentPage 현재 page
	 * @param pageSize 페이지별 row 수
	 * @param dispPageNum 페이지표시 수
	 * @return
	 */
	public static PageInfo getPageInfo(long totalCount, int currentPage, int pageSize, int dispPageNum) {
		
		log.debug("★★★★★ totalCount, currentPage, pageSize, dispPageNum ==> {}, {}, {}, {}", totalCount, currentPage, pageSize, dispPageNum);
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageSize(pageSize);
		
		int endPage = (int) (Math.ceil(currentPage / (double) dispPageNum) * dispPageNum);
		pageInfo.setEndPage(endPage);
		//log.debug("★★★★★ endPage ==> {}", endPage);
		
		int startPage = (endPage - dispPageNum) + 1;
		pageInfo.setStartPage(startPage);
		//log.debug("★★★★★ startPage ==> {}", startPage);
		
		// endPage가 모자랄 경우 보정
		int tempEndPage = (int) (Math.ceil(totalCount / (double) pageSize));
		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		List<Integer> pageNumbers = new ArrayList<Integer>();
		for (int i = startPage; i <= endPage; i++ ) {
			pageNumbers.add(i);
		}
		pageInfo.setPageNumbers(pageNumbers);
		
		boolean isPrev = startPage == 1 ? false : true;
		pageInfo.setIsPrev(isPrev);
		//log.debug("★★★★★ isPrev ==> {}", isPrev);
		boolean isNext = endPage * pageSize >= totalCount ? false : true;
		pageInfo.setIsNext(isNext);
		//log.debug("★★★★★ isNext ==> {}", isNext);
	
		return pageInfo;
	}

}
