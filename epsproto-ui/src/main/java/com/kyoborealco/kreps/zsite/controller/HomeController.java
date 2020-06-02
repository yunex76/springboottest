package com.kyoborealco.kreps.zsite.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kyoborealco.kreps.model.dto.DataTable;
import com.kyoborealco.kreps.model.entity.TestBoard;
import com.kyoborealco.kreps.model.entity.test.Person;
import com.kyoborealco.kreps.repository.TestBoardRepository;

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

	@NonNull
	private final TestBoardRepository testBoardRepository;

	@GetMapping("/")
    public String getLanding(Model model) {
    	
    	log.debug("★★★★★ home controller ==> {}", "index");
    	
        return "index";
    }
	
	@GetMapping("/MyDataTablePage")
	public String myDataTablePage(Model model) {

	    return "/test/MyDataTablePage";
	}
	
	@GetMapping("/test1")
	public String getTableBordered(Model model) {

	    return "/test/test1";
	}
	
	@RequestMapping(path = "/Data/Person", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Person> getPersonData() {
	    
		// System.out.println("I was called!");

//	    SearchCriteria searchCriteria = new SearchCriteria();
//	    searchCriteria.setOrderByString("name");
//	    searchCriteria.setUsePaging(false);
//	    searchCriteria.setFIRST_NUMBER(null);
//	    searchCriteria.setLAST_NUMBER(null);
	    
	    List<Person> list = new ArrayList<Person>();
	    list.add(new Person("홍길동", "111-02-33553", "2000-05-01", "남"));
	    list.add(new Person("이순신", "111-01-55553", "1990-04-01", "남"));
	    list.add(new Person("강감찬", "111-02-22211", "1980-04-01", "남"));

	    list.add(new Person("홍길동", "111-02-33553", "2000-05-01", "남"));
	    list.add(new Person("이순신", "111-01-55553", "1990-04-01", "남"));
	    list.add(new Person("강감찬", "111-02-22211", "1980-04-01", "남"));

	    list.add(new Person("홍길동", "111-02-33553", "2000-05-01", "남"));
	    list.add(new Person("이순신", "111-01-55553", "1990-04-01", "남"));
	    list.add(new Person("강감찬", "111-02-22211", "1980-04-01", "남"));

	    list.add(new Person("홍길동", "111-02-33553", "2000-05-01", "남"));
	    list.add(new Person("이순신", "111-01-55553", "1990-04-01", "남"));
	    list.add(new Person("강감찬", "111-02-22211", "1980-04-01", "남"));

	    list.add(new Person("홍길동", "111-02-33553", "2000-05-01", "남"));
	    list.add(new Person("이순신", "111-01-55553", "1990-04-01", "남"));
	    list.add(new Person("강감찬", "111-02-22211", "1980-04-01", "남"));

	    list.add(new Person("홍길동", "111-02-33553", "2000-05-01", "남"));
	    list.add(new Person("이순신", "111-01-55553", "1990-04-01", "남"));
	    list.add(new Person("강감찬", "111-02-22211", "1980-04-01", "남"));

	    list.add(new Person("홍길동", "111-02-33553", "2000-05-01", "남"));
	    list.add(new Person("이순신", "111-01-55553", "1990-04-01", "남"));
	    list.add(new Person("강감찬", "111-02-22211", "1980-04-01", "남"));

	    list.add(new Person("홍길동", "111-02-33553", "2000-05-01", "남"));
	    list.add(new Person("이순신", "111-01-55553", "1990-04-01", "남"));
	    list.add(new Person("강감찬", "111-02-22211", "1980-04-01", "남"));

	    list.add(new Person("홍길동", "111-02-33553", "2000-05-01", "남"));
	    list.add(new Person("이순신", "111-01-55553", "1990-04-01", "남"));
	    list.add(new Person("강감찬", "111-02-22211", "1980-04-01", "남"));

	    list.add(new Person("홍길동", "111-02-33553", "2000-05-01", "남"));
	    list.add(new Person("이순신", "111-01-55553", "1990-04-01", "남"));
	    list.add(new Person("강감찬", "111-02-22211", "1980-04-01", "남"));
	    
	    // return myMapperService.getPeople(searchCriteria); //returns an ArrayList<Person>
	    return list;
	}
	
	@GetMapping("/my/url")
	public String listAll(
	        Model model,
	        @PageableDefault(size=200, sort="name") Pageable pageable)
	{
	    Page<TestBoard> page = testBoardRepository.paginateTestBoard("", pageable);
	    model.addAttribute("page", page);
	    return "/test/path-to-html-resource";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/my/url/list")
	public ResponseEntity listAllTable(@RequestParam("draw") int draw,
	                                  @RequestParam("start") int start,
	                                  @RequestParam("length") int length) {

	    int page = start / length; //Calculate page number

	    Pageable pageable = PageRequest.of(
	            page,
	            length,
	            Sort.by("name").descending()
	    ) ;

	    Page<TestBoard> responseData = testBoardRepository.paginateTestBoard("", pageable);

	    DataTable<TestBoard> dataTable = new DataTable<TestBoard>();

	    dataTable.setData(responseData.getContent());
	    dataTable.setRecordsTotal(responseData.getTotalElements());
	    dataTable.setRecordsFiltered(responseData.getTotalElements());

	    dataTable.setDraw(draw);
	    dataTable.setStart(start);

	    return ResponseEntity.ok(dataTable);

	}
	
}
