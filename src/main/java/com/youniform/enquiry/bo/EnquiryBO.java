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

}
