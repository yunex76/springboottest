package com.kyoborealco.kreps.zsite.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kyoborealco.kreps.model.dto.DataTable;
import com.kyoborealco.kreps.model.dto.TestBoardDTO;
import com.kyoborealco.kreps.model.dto.mapper.TestBoardMapper;
import com.kyoborealco.kreps.model.entity.TestBoard;
import com.kyoborealco.kreps.repository.TestBoardRepository;
import com.kyoborealco.kreps.zsite.criteria.TestBoardCriteria;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Datatables 컨트롤러 클래스 
 *
 */
@Slf4j
@RequiredArgsConstructor
@Controller
public class DatatablesController {

	@NonNull
	private final TestBoardRepository testBoardRepository;

	@RequestMapping("/datatables/autofill")
    public String getDatatablesAutofill(Model model,
            @RequestParam(value="name", required=false) Optional<String> name,
            @RequestParam(value="position", required=false) Optional<String> position,
            @RequestParam(value="startDateFrom", required=false) Optional<String> startDateFrom,
            @RequestParam(value="startDateTo", required=false) Optional<String> startDateTo) {

    	log.debug("★★★★★ datatables - autofill controller ==> name = {} / position = {} / startDate = {} ~ {}",
    			name, position, startDateFrom, startDateTo);
    	
		TestBoardCriteria criteria = new TestBoardCriteria(name.orElse(""),
				position.orElse(""), startDateFrom.orElse(""), startDateTo.orElse(""));
		
		model.addAttribute("criteria", criteria);
    	
        return "/view/datatables/autofill";
    }
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/datatables/autofill/list")
	public ResponseEntity listDatatablesAutofill(@RequestParam("draw") int draw,
	                                  @RequestParam("start") int start,
	                                  @RequestParam("length") int length,
	                                  @RequestParam(value="name", required=false) Optional<String> name,
	                                  @RequestParam(value="position", required=false) Optional<String> position,
	                                  @RequestParam(value="startDateFrom", required=false) Optional<String> startDateFrom,
	                                  @RequestParam(value="startDateTo", required=false) Optional<String> startDateTo) {

		TestBoardCriteria criteria = new TestBoardCriteria(name.orElse(""),
				position.orElse(""), startDateFrom.orElse(""), startDateTo.orElse(""));
		
    	log.debug("★★★★★ listDatatablesAutofill() ==> start = {} / length = {} / name = {} / position = {} / startDate = {} ~ {}",
    			start, length, criteria.getName(), criteria.getPosition(), criteria.getStartDateFrom(), criteria.getStartDateTo());

	    int page = start / length; //Calculate page number

	    Pageable pageable = PageRequest.of(
	            page,
	            length
	    ) ;

	    Page<TestBoardDTO> responseData = testBoardRepository.paginateTestBoardDTO(
	    		criteria.getName(), criteria.getPosition(), criteria.getStartDateFrom(), criteria.getStartDateTo(), pageable);

	    DataTable<TestBoardDTO> dataTable = new DataTable<TestBoardDTO>();

	    dataTable.setData(responseData.getContent());
	    dataTable.setRecordsTotal(responseData.getTotalElements());
	    dataTable.setRecordsFiltered(responseData.getTotalElements());

	    dataTable.setDraw(draw);
	    dataTable.setStart(start);
	    dataTable.setSearchCriteria(criteria);
	    
	    return ResponseEntity.ok(dataTable);

	}
	
	@RequestMapping("/datatables/autofill/input")
    public String getDatatablesAutofillInput(Model model,
    		@RequestParam(value="id", required=false) Optional<Integer> id,
            @RequestParam(value="name", required=false) Optional<String> name,
            @RequestParam(value="position", required=false) Optional<String> position,
            @RequestParam(value="startDateFrom", required=false) Optional<String> startDateFrom,
            @RequestParam(value="startDateTo", required=false) Optional<String> startDateTo) {
    	
		TestBoardCriteria criteria = new TestBoardCriteria(name.orElse(""),
				position.orElse(""), startDateFrom.orElse(""), startDateTo.orElse(""));
		
    	log.debug("★★★★★ datatables - autofill input controller ==> {} / id = {}", "datatables_autofill_input", id.orElse(0));
    	
		model.addAttribute("boardId", id.orElse(0));
		model.addAttribute("criteria", criteria);

        return "/view/datatables/autofill_input";
    }
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/datatables/autofill/get/{id}")
	public ResponseEntity getDatatablesAutofill(@PathVariable("id") int id) {

    	log.debug("★★★★★ getDatatablesAutofill() ==> id = {}", id);

    	TestBoard testBoard = testBoardRepository.findById(id).orElseGet(TestBoard::new);
    	
    	TestBoardDTO dto = new TestBoardDTO();
		TestBoardMapper.mapper().map(testBoard, dto);	// entity -> dto
    	
	    return ResponseEntity.ok(dto);
	}
	
	@RequestMapping(value="/datatables/autofill/save", method=RequestMethod.POST)
	@ResponseBody
	@Transactional
    public Map<String, Object> saveDatatablesAutofill(@RequestBody Map<String, Object> params) {

    	log.debug("★★★★★ saveDatatablesAutofill() ==> params = {}", params);
    	
    	int boardId = Integer.parseInt(params.get("boardId").toString());
    	boolean isUpdate = false;
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
				
		try {
			// 
			TestBoard testBoard = testBoardRepository.findById(boardId).orElseGet(TestBoard::new);
			
			if ( testBoard.getBoardId() != null && testBoard.getBoardId() > 0 ) {
				isUpdate = true;
			}
			
			testBoard.setName(params.get("name").toString());
			testBoard.setPosition(params.get("position").toString());
			testBoard.setOffice(params.get("office").toString());
			testBoard.setAge(Integer.parseInt(params.get("age").toString()));
			testBoard.setStartDate(params.get("startDate").toString());
			testBoard.setSalary(Integer.parseInt(params.get("salary").toString()));

	    	TestBoardDTO dto = new TestBoardDTO();
			TestBoardMapper.mapper().map(testBoardRepository.save(testBoard), dto);	// entity -> dto
			
			resultMap.put("data", dto);
		}
		catch(Exception e) {
			resultMap.put("message", e.getMessage());
			return resultMap;
		}
		
		if ( isUpdate ) {
			resultMap.put("message", "수정되었습니다.");
		}
		else {
			resultMap.put("message", "저장되었습니다.");
		}
		
		return resultMap;
	}
	
	@RequestMapping(value="/datatables/autofill/remove", method=RequestMethod.POST)
	@ResponseBody
	@Transactional
    public Map<String, Object> removeDatatablesAutofill(@RequestBody Map<String, Object> params) {

    	log.debug("★★★★★ removeDatatablesAutofill() ==> params = {}", params);
    	
    	int boardId = Integer.parseInt(params.get("boardId").toString());
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
				
		try {
			// 
			testBoardRepository.deleteById(boardId);
		}
		catch(Exception e) {
			resultMap.put("message", e.getMessage());
			return resultMap;
		}
		
		resultMap.put("message", "삭제되었습니다.");
		return resultMap;
	}
}
