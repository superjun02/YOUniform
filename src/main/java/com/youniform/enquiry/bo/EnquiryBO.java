package com.youniform.enquiry.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youniform.enquiry.mapper.EnquiryMapper;

@Service
public class EnquiryBO {
	@Autowired
	private EnquiryMapper enquiryMapper;
	
	public int addEnquiry(Integer userId, String division, String subject, String description) {
		return enquiryMapper.insertEnquiry(userId, division, subject, description);
	}

	public int updateEnquiryByAnswerAndId(String answer, int id) {
		return enquiryMapper.updateEnquiryByAnswerAndId(answer, id);
	}

	public void deleteEnquiryById(int id) {
		enquiryMapper.deleteEnquiryById(id);
	}

	public int getTotalItemsByDivision(String division, Integer userId) {
		return enquiryMapper.selectTotalItemsByDivision(division, userId);
	}

}
