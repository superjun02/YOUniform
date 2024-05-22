package com.youniform.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.youniform.basket.bo.BasketBO;
import com.youniform.order.bo.OrderBO;
import com.youniform.order.domain.Order;
import com.youniform.uniform.bo.UniformBO;
import com.youniform.uniform.domain.Uniform;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/order")
@Controller
public class OrderController {
	@Autowired
	private UniformBO uniformBO;
	
	@Autowired
	private BasketBO basketBO;
	
	@Autowired
	private OrderBO orderBO;
	
	@GetMapping("/purchase-view")
	public String purchaseView(Model model,
			HttpSession session,
			@RequestParam(value = "uniformId", required = false) Integer id) {
		model.addAttribute("viewName", "order/purchase");
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		if (userId == null) {
			return "redirect:/home/home-view";
		}
		
		if (id == null) {
			List<Uniform> uniformList = basketBO.getBasketByUserId(userId);
			
			model.addAttribute("uniformId", "null");
			model.addAttribute("uniformList", uniformList);
		} else {
			Uniform uniform = uniformBO.getUniformById(id);
			
			List<Uniform> uniformList = new ArrayList<>();
			uniformList.add(uniform);
			
			model.addAttribute("uniformId", uniform.getId());
			model.addAttribute("uniformList", uniformList);
		}
		
		return "template/layout";
	}
	
	@GetMapping("/order-list-view")
	public String orderListView(Model model,
			HttpSession session) {
		model.addAttribute("viewName", "order/orderList");
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		if (userId == null) {
			return "redirect:/home/home-view";
		}
		
		List<Order> orderList = orderBO.getOrderListByUserId(userId);
		
		model.addAttribute("orderList", orderList);
		return "template/layout";
	}
}
