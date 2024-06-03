package com.youniform.uniform.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youniform.uniform.domain.Request;
import com.youniform.uniform.domain.Uniform;
import com.youniform.uniform.mapper.UniformMapper;
import com.youniform.user.repository.UserRepository;

@Service
public class RequestBO {
	@Autowired
	private UniformMapper uniformMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Request> getReuestList() {
		List<Request> requestList = new ArrayList<>();
		
		List<Uniform> uniformList = uniformMapper.selectUniformRequestList();
		
		Iterator<Uniform> iter = uniformList.iterator();
		
		while (iter.hasNext()) {
			Uniform uniform = iter.next();
			
			Request request = new Request();
			
			request.setUserId(userRepository.findById(uniform.getUserId()).orElse(null).getLoginId());
			request.setUniform(uniform);
			
			requestList.add(request);
		}
		
		return requestList;
	}

}
