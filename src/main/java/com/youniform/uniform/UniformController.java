package com.youniform.uniform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.youniform.uniform.bo.UniformBO;
import com.youniform.uniform.domain.Uniform;

@RequestMapping("/uniform")
@Controller
public class UniformController {
	@Autowired
	private UniformBO uniformBO;
	
	@GetMapping("/detail-view")
	public String homeView(Model model,
			@RequestParam("id") int id) {
		model.addAttribute("viewName", "uniform/detail");
		
		Uniform uniform = uniformBO.getUniformById(id);
		model.addAttribute("uniform", uniform);
		return "template/layout";
	}
}
