package com.youniform.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.youniform.uniform.bo.UniformBO;
import com.youniform.uniform.domain.Uniform;

@RequestMapping("/order")
@Controller
public class OrderController {
	@Autowired
	private UniformBO uniformBO;
	
	@GetMapping("/purchase-view")
	public String purchaseView(Model model,
			@RequestParam("uniformId") int id) {
		model.addAttribute("viewName", "order/purchase");
		
		Uniform uniform = uniformBO.getUniformById(id);
		model.addAttribute("uniform", uniform);
		return "template/layout";
	}
	
}
