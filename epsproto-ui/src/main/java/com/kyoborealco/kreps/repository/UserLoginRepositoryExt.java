package com.kyoborealco.kreps.repository;

import com.kyoborealco.kreps.model.entity.Users;

public interface UserLoginRepositoryExt {

	Users findByIdWithFetch(String userLoginId);
}
