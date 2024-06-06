package com.youniform.enquiry;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.youniform.common.TelegramService;
import com.youniform.enquiry.bo.EnquiryBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/enquiry")
@RestController
public class EnquiryRestController {
	@Autowired
	private EnquiryBO enquiryBO;
	
	@Autowired
	private TelegramService telegramService;
	
	@PostMapping("/create")
	public Map<String, Object> create(Model model,
			HttpSession session,
			@RequestParam("division") String division,
			@RequestParam("subject") String subject,
			@RequestParam("description") String description) {
		Map<String, Object> result = new HashMap<>();
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		int columNum = enquiryBO.addEnquiry(userId, division, subject, description);
		
		if (columNum == 1) {
			String loginId = (String) session.getAttribute("loginId");
			telegramService.sendMessage("-4249005507", loginId + "님의 문의가 들어왔습니다. 확인 후 답변 부탁드립니다.");
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 300);
			result.put("error_message", "등록에 실패했습니다.");
		}
		return result;
	}
	
	@PostMapping("/add-reply")
	public Map<String, Object> addReply(Model model,
			HttpSession session,
			@RequestParam("answer") String answer,
			@RequestParam("id") int id) {
		Map<String, Object> result = new HashMap<>();
		
		int columNum = enquiryBO.updateEnquiryByAnswerAndId(answer, id);
		
		if (columNum == 1) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 300);
			result.put("error_message", "등록에 실패했습니다.");
		}
		return result;
	}
	
	@PostMapping("/delete")
	public Map<String, Object> delete(Model model,
			HttpSession session,
			@RequestParam("id") int id) {
		Map<String, Object> result = new HashMap<>();
		
		enquiryBO.deleteEnquiryById(id);
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
}
