package com.youniform.basket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youniform.uniform.bo.UniformBO;
import com.youniform.uniform.domain.Uniform;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/basket")
@Controller
public class BasketController {
	@Autowired
	private UniformBO uniformBO;
	
	@GetMapping("/basket-view")
	public String basketView(Model model,
			HttpSession session) {
		model.addAttribute("viewName", "basket/basketList");
		int userId = (int) session.getAttribute("userId");
		
		List<Uniform> uniformList = uniformBO.getUniformListByBasket(userId);
		
		model.addAttribute("uniformList", uniformList);
		return "template/layout";
	}
}
