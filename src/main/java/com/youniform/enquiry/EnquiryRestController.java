package com.youniform.enquiry;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.youniform.enquiry.bo.EnquiryBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/enquiry")
@RestController
public class EnquiryRestController {
	@Autowired
	private EnquiryBO enquiryBO;
	
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
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 300);
			result.put("error_message", "등록에 실패했습니다.");
		}
		return result;
	}
}
