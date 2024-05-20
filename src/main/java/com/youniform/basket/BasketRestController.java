package com.youniform.basket;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.youniform.basket.bo.BasketBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/basket")
@RestController
public class BasketRestController {
	@Autowired
	private BasketBO basketBO;
	
	@PostMapping("/add_basket")
	public Map<String, Object> addBasket(
			@RequestParam("uniformId") int uniformId,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		int userId = (int) session.getAttribute("userId");
		
		if (basketBO.getBasketByUserIdAndUniformId(userId, uniformId)) {
			result.put("code", 500);
			result.put("error_message", "제품당 한번씩만 장바구니에 넣을 수 있습니다.");
			
			return result;
		}
		
		int columNum = basketBO.addBasket(userId, uniformId);
		
		if (columNum == 1) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 300);
			result.put("error_message", "장바구니 추가에 실패했습니다.");
		}
		
		return result;
	}
	
	@PostMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("uniformId") int uniformId) {
		Map<String, Object> result = new HashMap<>();
		
		basketBO.deleteBasketByUniformId(uniformId);
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
}
