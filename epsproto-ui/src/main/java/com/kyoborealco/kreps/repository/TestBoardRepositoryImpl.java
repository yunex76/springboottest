package com.kyoborealco.kreps.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.kyoborealco.kreps.model.entity.TestBoard;
import com.kyoborealco.kreps.model.dto.TestBoardDTO;
import com.kyoborealco.kreps.model.entity.QTestBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestBoardRepositoryImpl implements TestBoardRepositoryExt {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<TestBoard> retrieveTestBoard(String name) {

		log.info("★★★★★ name => {}", name);
		
		JPAQueryFactory query = new JPAQueryFactory(em); // JPA 쿼리

		QTestBoard qTestBoard = QTestBoard.testBoard;
		
		// where 조건 builder
		BooleanBuilder builder = new BooleanBuilder();
		if (name != null && name.length() > 0) {
			builder.and(qTestBoard.name.like("%"+name+"%"));
		}
		
		// 
		List<TestBoard> list 
			= query.selectFrom(qTestBoard)
				.where(builder)
				.orderBy(qTestBoard.boardId.desc())
				.fetch();

		log.debug("★★★★★ pollmain_list : {}", list.size());

		return list;
	}


	@Override
	public Page<TestBoard> paginateTestBoard(String name, Pageable pageable) {

		log.info("★★★★★ name => {}", name);
		
		JPAQueryFactory query = new JPAQueryFactory(em); // JPA 쿼리

		QTestBoard qTestBoard = QTestBoard.testBoard;
		
		// where 조건 builder
		BooleanBuilder builder = new BooleanBuilder();
		if (name != null && name.length() > 0) {
			builder.and(qTestBoard.name.like("%"+name+"%"));
		}

		// 
		QueryResults<TestBoard> results 
			= query.selectFrom(qTestBoard)
				.where(builder)
				.orderBy(qTestBoard.boardId.desc())
				.limit(pageable.getPageSize())
				.offset(pageable.getOffset())
				.fetchResults();

		log.debug("★★★★★ testBoard_list : {}", results.getResults().size());

		return new PageImpl<>(results.getResults(), pageable, results.getTotal());
	}

	@Override
	public Page<TestBoardDTO> paginateTestBoardDTO(String name, String position, String startDateFrom, String startDateTo, Pageable pageable) {

		JPAQueryFactory query = new JPAQueryFactory(em); // JPA 쿼리

		QTestBoard qTestBoard = QTestBoard.testBoard;
		
		// where 조건 builder
		BooleanBuilder builder = new BooleanBuilder();
		if (name != null && name.length() > 0) {
			builder.and(qTestBoard.name.like("%"+name+"%"));
		}
		if (position != null && position.length() > 0) {
			builder.and(qTestBoard.position.like("%"+position+"%"));
		}
		if ( ( startDateFrom != null && startDateFrom.length() > 0 )
				&& ( startDateTo != null && startDateTo.length() > 0 ) ) {
			builder.and(qTestBoard.startDate.between(startDateFrom, startDateTo));
		}

		// 
		QueryResults<TestBoardDTO> results 
			= query.select(Projections.constructor(TestBoardDTO.class,
					qTestBoard.boardId,
					qTestBoard.name,
					qTestBoard.position,
					qTestBoard.office, 
					qTestBoard.age,
					qTestBoard.startDate,
					qTestBoard.salary
				))
				.from(qTestBoard)
				.where(builder)
				.orderBy(qTestBoard.boardId.desc())
				.limit(pageable.getPageSize())
				.offset(pageable.getOffset())
				.fetchResults();

		log.debug("★★★★★ testBoard_list : {}", results.getResults().size());

		return new PageImpl<>(results.getResults(), pageable, results.getTotal());
	}
	
}
