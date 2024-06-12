package com.youniform.enquiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.youniform.common.DataSizeChecker;
import com.youniform.enquiry.bo.EnquiryBO;
import com.youniform.enquiry.bo.PostBO;
import com.youniform.enquiry.domain.Post;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/enquiry")
@Controller
public class EnquiryController {
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private EnquiryBO enquiryBO;
	
	@Autowired
	private DataSizeChecker sizeChecker;
	
	@GetMapping("/enquiry-list-view")
	public String enquiryListView(Model model,
			HttpSession session,
			@RequestParam(value="division", required = false) String division,
			@RequestParam(value="page", defaultValue = "1") int page,
            @RequestParam(value="pageSize", defaultValue = "5") int pageSize) {
		model.addAttribute("viewName", "enquiry/enquiryList");
		
		Integer userId = (Integer) session.getAttribute("userId");
	
		if (userId == null) {
			return "redirect:/home/home-view";
		}

		int totalItems = enquiryBO.getTotalItemsByDivision(division, userId);	
		
		Integer totalPages = sizeChecker.getTotalPages(totalItems, pageSize);
		
	    if (totalItems % pageSize != 0) {
	        totalPages++;
	    }
		
		if (page < 1) {
	        page = 1;
	    } else if (page > totalPages) {
	        page = totalPages;
	    }
		
		PageInfo<Post> pageInfo = postBO.getPostListByUserId(division, userId, page, pageSize);
        model.addAttribute("postList", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("division", division);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
		
		return "template/layout";
	}
	
	@GetMapping("/enquiry-detail-view")
	public String enquiryDetailView(Model model,
			HttpSession session,
			@RequestParam("id") int id,
			@RequestParam("userId") int userId) {
		Integer sessionId = (Integer) session.getAttribute("userId");

		if (sessionId != 1 && (sessionId == null || userId != sessionId)) {
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
