package com.ind.sevice;

import java.util.Optional;

import com.ind.entity.UserEntity;

public interface UserService {
	
	public Integer saveUser(UserEntity user);
	
	public Optional<UserEntity> getOneUser(Integer id);

}
