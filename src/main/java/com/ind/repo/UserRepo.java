package com.ind.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ind.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {

	public Optional<UserEntity> findByUserEmail(String userEmail);

}
