package com.kyoborealco.kreps.repository;

import org.springframework.stereotype.Repository;

import com.kyoborealco.kreps.model.entity.TestBoard;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TestBoardRepository extends JpaRepository<TestBoard, Integer>, TestBoardRepositoryExt {
	
}
