package com.kyoborealco.kreps.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.kyoborealco.kreps.model.entity.Users;
import com.kyoborealco.kreps.model.entity.QUsers;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class UserLoginRepositoryImpl implements UserLoginRepositoryExt {
	
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public Users findByIdWithFetch(String userLoginId) {
		
		JPAQueryFactory query = new JPAQueryFactory(em);  // JPA 쿼리

		QUsers qUsers = QUsers.users;
		
		//where 조건 builder
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qUsers.userId.eq(userLoginId));
		
		Users result = query.selectFrom(qUsers)
		.where(builder)
		.fetchOne();
		
		return result;
	}

}
