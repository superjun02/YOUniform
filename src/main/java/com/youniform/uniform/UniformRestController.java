package com.youniform.uniform;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.youniform.uniform.bo.UniformBO;

@RequestMapping("/uniform")
@RestController
public class UniformRestController {
	@Autowired
	private UniformBO uniformBO;
	
	@PostMapping("/delete") 
	public Map<String, Object> delete(
			@RequestParam("uniformId") int id,
			Model model) {
		Map<String, Object> result = new HashMap<>();
		
		uniformBO.deleteUniformById(id);
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
}
