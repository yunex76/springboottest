package com.kyoborealco.kreps.repository;

import com.kyoborealco.kreps.model.dto.TestBoardDTO;
import com.kyoborealco.kreps.model.entity.TestBoard;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TestBoardRepositoryExt {

	List<TestBoard> retrieveTestBoard(String name);
	Page<TestBoard> paginateTestBoard(String name, Pageable pageable);
	
	Page<TestBoardDTO> paginateTestBoardDTO(String name, String position, String startDateFrom, String startDateTo, Pageable pageable);
}
