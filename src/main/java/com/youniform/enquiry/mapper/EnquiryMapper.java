package com.youniform.enquiry.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.youniform.enquiry.domain.Enquiry;

@Mapper
public interface EnquiryMapper {

	public List<Enquiry> selectEnquiryListByUserId(Integer userId);

	public List<Enquiry> selectEnquiryListByDivisionAndUserId(
			@Param("division") String division,
			@Param("userId") Integer userId);

	public Enquiry selectEnquiryById(int id);

	public int insertEnquiry(
			@Param("userId") Integer userId,
			@Param("division") String division,
			@Param("subject") String subject,
			@Param("description") String description);

	public int updateEnquiryByAnswerAndId(
			@Param("answer") String answer,
			@Param("id") int id);

	public void deleteEnquiryById(int id);

	public int selectTotalItems(Integer userId);
	
	public int selectTotalItemsByDivision(
			@Param("division") String division,
			@Param("userId") Integer userId);

}
