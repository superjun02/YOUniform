package com.youniform.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youniform.common.EncryptUtils;
import com.youniform.user.entity.UserEntity;
import com.youniform.user.repository.UserRepository;

@Service
public class UserBO {
	@Autowired
	private UserRepository userRepository;
	public UserEntity getUserEntityByLoginIdPassword(String loginId, String password) {
		String hashedPassword = EncryptUtils.hashString(password);
		
		return userRepository.findByLoginIdAndPassword(loginId, hashedPassword);
	}
	public UserEntity getUserEntityByLoginId(String loginId) {
		return userRepository.findByLoginId(loginId);
	}
	public Integer addUser(String loginId, String password, String name, String email) {
		String hashedPassword = EncryptUtils.hashString(password);
		
		UserEntity user = userRepository.save(UserEntity.builder()
				.loginId(loginId)
				.password(hashedPassword)
				.name(name)
				.email(email).build());

		return user == null ? null : user.getId();
	}

}
