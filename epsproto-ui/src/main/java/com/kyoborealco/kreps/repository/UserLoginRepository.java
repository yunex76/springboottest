package com.kyoborealco.kreps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kyoborealco.kreps.model.entity.Users;

@Repository
public interface UserLoginRepository extends JpaRepository<Users, String>, UserLoginRepositoryExt {
	
}
