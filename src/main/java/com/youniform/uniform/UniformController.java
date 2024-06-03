package com.youniform.uniform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.youniform.uniform.bo.RequestBO;
import com.youniform.uniform.bo.UniformBO;
import com.youniform.uniform.domain.Request;
import com.youniform.uniform.domain.Uniform;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/uniform")
@Controller
public class UniformController {
	@Autowired
	private UniformBO uniformBO;
	
	@Autowired
	private RequestBO requestBO;
	
	@GetMapping("/detail-view")
	public String homeView(Model model,
			@RequestParam("id") int id) {
		model.addAttribute("viewName", "uniform/detail");
		
		Uniform uniform = uniformBO.getUniformById(id);
		model.addAttribute("uniform", uniform);
		return "template/layout";
	}
	
	@GetMapping("/request-view")
	public String requestView(Model model,
			HttpSession session) {
		model.addAttribute("viewName", "uniform/request");
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		if (userId == null) {
			return "redirect:/home/home-view";
		}
		
		return "template/layout";
	}
	
	@GetMapping("/request-list-view")
	public String requestListView(Model model,
			HttpSession session) {
		model.addAttribute("viewName", "uniform/requestList");
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		if (userId != 1) {
			return "redirect:/home/home-view";
		}
		
		List<Request> requestList = requestBO.getReuestList();
		
		model.addAttribute("requestList", requestList);
		return "template/layout";
	}
	
	@GetMapping("/request-check-view")
	public String requestCheckView(Model model,
			HttpSession session,
			@RequestParam("id") int id) {
		model.addAttribute("viewName", "uniform/requestCheck");
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		if (userId != 1) {
			return "redirect:/home/home-view";
		}
		
		Uniform uniform = uniformBO.getUniformById(id);
		model.addAttribute("uniform", uniform);
		return "template/layout";
	}
}
