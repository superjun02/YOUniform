package com.youniform.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.youniform.user.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	public UserEntity findByLoginIdAndPassword(
			@Param("loginId") String loginId,
			@Param("password") String password);
}
