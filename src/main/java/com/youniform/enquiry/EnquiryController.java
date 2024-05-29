package com.youniform.enquiry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.youniform.enquiry.bo.PostBO;
import com.youniform.enquiry.domain.Post;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/enquiry")
@Controller
public class EnquiryController {
	@Autowired
	private PostBO postBO;
	
	@GetMapping("/enquiry-list-view")
	public String enquiryListView(Model model,
			HttpSession session,
			@RequestParam(value="division", required = false) String division) {
		model.addAttribute("viewName", "enquiry/enquiryList");
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		if (userId == null) {
			return "redirect:/home/home-view";
		}
		
		List<Post> postlist = postBO.getPostList(division, userId);
		
		model.addAttribute("postList", postlist);
		model.addAttribute("division", division);
		
		return "template/layout";
	}
	
	@GetMapping("/enquiry-detail-view")
	public String enquiryDetailView(Model model,
			HttpSession session,
			@RequestParam("id") int id) {
		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			return "redirect:/home/home-view";
		}
		
		Post post = postBO.getPostById(id);
		
		model.addAttribute("post", post);
		model.addAttribute("viewName", "enquiry/enquiryDetail");
		return "template/layout";
	}
	
	@GetMapping("/enquiry-create-view")
	public String enquiryCreateView(Model model,
			HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			return "redirect:/home/home-view";
		}
		
		model.addAttribute("viewName", "enquiry/enquiryCreate");
		return "template/layout";
	}
}
