package com.youniform.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youniform.uniform.bo.UniformBO;
import com.youniform.uniform.domain.Uniform;

@RequestMapping("/home")
@Controller
public class HomeController {
	@Autowired
	private UniformBO uniformBO;
	
	@GetMapping("/home-view")
	public String homeView(Model model) {
		model.addAttribute("viewName", "home/home");
		
		List<Uniform> uniformList = uniformBO.getUniformList();
		model.addAttribute("uniformList", uniformList);
		return "template/layout";
	}
}
