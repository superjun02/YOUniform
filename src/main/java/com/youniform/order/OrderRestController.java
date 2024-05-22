package com.youniform.order;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.youniform.order.bo.OrderBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/order")
@RestController
public class OrderRestController {
	@Autowired
	private OrderBO orderBO;
	
	@PostMapping("/purchase")
	public Map<String, Object> purchase(
			@RequestParam("address") String address,
			@RequestParam(name = "uniformId", required = false) Integer uniformId,
			@RequestParam("totalPrice") int totalPrice,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		int userId = (int) session.getAttribute("userId");
		
		int columNum = orderBO.addOrder(userId, address, uniformId, totalPrice);
		
		if (columNum == 1) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 300);
			result.put("error_message", "구매에 실패했습니다.");
		}

		return result;
	}
	
	@PostMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("orderId") int orderId) {
		Map<String, Object> result = new HashMap<>();
		
		orderBO.deleteOrderByOrderId(orderId);
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
}
