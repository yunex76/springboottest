package com.kyoborealco.kreps.model.dto.mapper;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import com.kyoborealco.kreps.model.dto.TestBoardDTO;
import com.kyoborealco.kreps.model.entity.TestBoard;

public class TestBoardMapper {

	private static ModelMapper modelMapper = new ModelMapper();
	
	static {
		// multiple source property hierarchies 오류발생시 적용
		modelMapper.getConfiguration().setAmbiguityIgnored(true);  // 모호성 무시		
		// source -> null proprety는 mapping 제외
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

		// =====================================================================
		
		TypeMap<TestBoard, TestBoardDTO> map2NewDTO
			= modelMapper.createTypeMap(TestBoard.class, TestBoardDTO.class);
		map2NewDTO.addMappings(mapper -> {
			// mapper.map(src -> src.getAcademicIdx(), JobAcademicSiteDTO::setAcademicIdx);
		});

		TypeMap<TestBoardDTO, TestBoard> map2NewEntity
			= modelMapper.createTypeMap(TestBoardDTO.class, TestBoard.class);
		map2NewEntity.addMappings(mapper -> {
			// mapper.map(src -> src.getAcademicIdx(), RecruitAcademic::setAcademicIdx);
		});
		
	}

	public static ModelMapper mapper() {
		
		return modelMapper;
	}
}
