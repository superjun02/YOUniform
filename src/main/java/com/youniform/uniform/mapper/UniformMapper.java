package com.youniform.uniform.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.youniform.uniform.domain.Uniform;

@Mapper
public interface UniformMapper {

	public List<Uniform> selectUniformList();

	public Uniform selectUniformById(int id);

	public void deleteUniformById(int id);

	public int insertUniform(
			@Param("userId") Integer userId,
			@Param("league") String league,
			@Param("subject") String subject,
			@Param("description") String description,
			@Param("image") String image,
			@Param("price") int price);

}
