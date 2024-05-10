package com.youniform.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youniform.user.entity.UserEntity;
import com.youniform.user.repository.UserRepository;

@Service
public class UserBO {
	@Autowired
	private UserRepository userRepository;
	public UserEntity getUserEntityByLoginIdPassword(String loginId, String hashedPassword) {
		return userRepository.findByLoginIdAndPassword(loginId, hashedPassword);
	}

}
