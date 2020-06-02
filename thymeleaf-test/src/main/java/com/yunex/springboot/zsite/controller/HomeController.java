package com.yunex.springboot.zsite.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//import com.kyoborealco.krweb.model.dto.board.Board4HomeNoticeHeadDTO;
//import com.kyoborealco.krweb.model.entity.Alimi;
//import com.kyoborealco.krweb.repository.AlimiRepository;
//import com.kyoborealco.krweb.repository.board.Board4HomeNoticeRepository;
//import com.kyoborealco.krweb.zsite.dto.AlimiSiteDTO;
//import com.kyoborealco.krweb.zsite.dto.HomeNoticeHeadSiteDTO;
//import com.kyoborealco.krweb.zsite.dto.mapper.HomeAlimiSiteMapper;
//import com.kyoborealco.krweb.zsite.dto.mapper.HomeNoticeSiteMapper;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Home 컨트롤러 클래스 
 *
 */
@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {

	private static final String DISPLAY_YES = "Y"; // 표시여부
	
//	@NonNull
//	private final Board4HomeNoticeRepository homeNoticeRepository;
//	
//	@NonNull
//	private final AlimiRepository alimiRepository;
	
	@GetMapping("/")
    public String getLanding(Model model) {
    	
    	log.debug("★★★★★ home controller ==> {}", "index");
    	
    	// 공지사항 목록 조회
		// parameter로 전달받은 page 조회조건으로 Pageable 생성
//		Pageable pageable = PageRequest.of(0, 5); // 최종 5건만 조회
//		// 업무조회조건과 page처리 조건 같이 전달
//		Page<Board4HomeNoticeHeadDTO> pageResult = homeNoticeRepository.paginateBoard4HomeNotice(DISPLAY_YES, null, null, null,pageable);
//		List<HomeNoticeHeadSiteDTO> dtos = toDtoHomeNotice(pageResult.getContent());
//		// 변환된dtos page결과 생성 (repository에서 얻은 page결과 전달)
//		Page<HomeNoticeHeadSiteDTO> homeNoticePage = new PageImpl<>(dtos, pageResult.getPageable(), pageResult.getTotalElements());
		
		
		// 팝업알림 조회
		Date currentDate = new Date();
//		List<Alimi> list = alimiRepository.availableAlimiList(currentDate, "Y");
//		Alimi alimiResult = null;
//		AlimiSiteDTO homeAlimiPopup = null; 
//		if (list != null && list.size() > 0) {
//			alimiResult = list.get(0);
//			homeAlimiPopup = toDtoHomeAlimi(alimiResult);
//		} else {
//			homeAlimiPopup = new AlimiSiteDTO();
//			homeAlimiPopup.setAlimiId(0); // 표시할 팝업알리미가 없을 경우 (ID == 0) 
//		}
		
		// add attribute
//		model.addAttribute("homeNoticePage", homeNoticePage);
//		model.addAttribute("homeAlimiPopup", homeAlimiPopup);
		
        return "index";
    }
	
	/**
	 * toDTO : entity -> DTO
	 * 
	 * @param alimiResult
	 * @return
	 */
//	private AlimiSiteDTO toDtoHomeAlimi(Alimi alimiResult) {
//
//		return HomeAlimiSiteMapper.mapper().map(alimiResult, AlimiSiteDTO.class);
//	}

	/**
	 * toDTO : DTO -> site DTOS
	 * 
	 * @param results::List
	 * @return
	 */
//	private List<HomeNoticeHeadSiteDTO> toDtoHomeNotice(List<Board4HomeNoticeHeadDTO> results) {
//		return results.stream().map(e -> HomeNoticeSiteMapper.mapper().map(e, HomeNoticeHeadSiteDTO.class))
//				.collect(Collectors.toList());
//	}
	
}
