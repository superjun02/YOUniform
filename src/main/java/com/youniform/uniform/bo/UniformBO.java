package com.youniform.uniform.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youniform.uniform.domain.Uniform;
import com.youniform.uniform.mapper.UniformMapper;

@Service
public class UniformBO {
	@Autowired
	private UniformMapper uniformMapper;
	
	public List<Uniform> getUniformList() {
		return uniformMapper.selectUniformList();
	}

	public Uniform getUniformById(int id) {
		return uniformMapper.selectUniformById(id);
	}

}
