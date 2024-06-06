package com.youniform.uniform;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.youniform.common.TelegramService;
import com.youniform.uniform.bo.UniformBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/uniform")
@RestController
public class UniformRestController {
	@Autowired
	private UniformBO uniformBO;
	
	@Autowired
	private TelegramService telegramService;
	
	@PostMapping("/delete") 
	public Map<String, Object> delete(
			@RequestParam("uniformId") int id) {
		Map<String, Object> result = new HashMap<>();
		
		uniformBO.deleteUniformById(id);
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
	
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("league") String league,
			@RequestParam("subject") String subject,
			@RequestParam("price") int price,
			@RequestParam("description")  String description,
			@RequestParam("file") MultipartFile file,
			@RequestParam("status") String status,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		
		Integer userId = (Integer) session.getAttribute("userId");
		String loginId = (String) session.getAttribute("loginId");
		
		Integer colNum = uniformBO.addUniform(userId, loginId, league, subject, price, description, file, status);
		
		if (colNum == 1) {
			telegramService.sendMessage("-4249005507", loginId + "님의 판매신청이 들어왔습니다. 확인 후 부탁드립니다.");
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("error_message", "매모가 저장되지 않았습니다.");
		}

		return result;
	}
	
	@PostMapping("/accept")
	public Map<String, Object> accept(
			@RequestParam("league") String league,
			@RequestParam("subject") String subject,
			@RequestParam("price") int price,
			@RequestParam("description")  String description,
			@RequestParam("status") String status,
			@RequestParam("id") int id,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		
		uniformBO.updateUniformById(id, league, subject, description, price, status);
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
}
