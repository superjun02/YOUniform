package com.youniform.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.youniform.common.DataSizeChecker;
import com.youniform.uniform.bo.UniformBO;
import com.youniform.uniform.domain.Uniform;

@RequestMapping("/home")
@Controller
public class HomeController {
	@Autowired
	private UniformBO uniformBO;
	
	@Autowired
	private DataSizeChecker sizeChecker;
	
	@GetMapping("/home-view")
	public String homeView(Model model,
			@RequestParam(value="page", required = false, defaultValue = "1") int page,
            @RequestParam(value="pageSize", required = false, defaultValue = "18") int pageSize) {
		model.addAttribute("viewName", "home/home");
		
		int totalItems = uniformBO.getTotalItems();
		
		Integer totalPages = sizeChecker.getTotalPages(totalItems, pageSize);
		
		if (page < 1) {
	        page = 1;
	    } else if (page > totalPages) {
	        page = totalPages;
	    }
		
		PageInfo<Uniform> pageInfo = uniformBO.getUniformList(page, pageSize);

		model.addAttribute("uniformList", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        
		return "template/layout";
	}
}
