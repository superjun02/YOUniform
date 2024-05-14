package com.youniform.uniform.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.youniform.uniform.domain.Uniform;

@Mapper
public interface UniformMapper {

	public List<Uniform> selectUniformList();

	public Uniform selectUniformById(int id);

}
