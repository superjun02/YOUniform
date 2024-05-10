package com.youniform.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.youniform.common.EncryptUtils;
import com.youniform.user.bo.UserBO;
import com.youniform.user.entity.UserEntity;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserRestController {
	@Autowired
	private UserBO userBO;
	
	
	@PostMapping("/sign-in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		
		String hashedPassword = EncryptUtils.md5(password);
		
		UserEntity user = userBO.getUserEntityByLoginIdPassword(loginId, hashedPassword);
		
		if (user != null) {
			session.setAttribute("userId", user.getId());
			session.setAttribute("loginId", user.getLoginId());
			session.setAttribute("name", user.getName());
			
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 300);
			result.put("error_message", "존재하지 않는 사용자입니다.");
		}
		return result;
	}
}
