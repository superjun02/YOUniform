package com.youniform.enquiry.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youniform.enquiry.domain.Enquiry;
import com.youniform.enquiry.domain.Post;
import com.youniform.enquiry.mapper.EnquiryMapper;
import com.youniform.user.entity.UserEntity;
import com.youniform.user.repository.UserRepository;

@Service
public class PostBO {
	@Autowired
	private EnquiryMapper enquiryMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Post> getPostListByUserId(String division, Integer userId) {
		List<Post> postList = new ArrayList<>();
		
		List<Enquiry> enquiryList = new ArrayList<>();
		
		if (division == null) {
			enquiryList = enquiryMapper.selectEnquiryListByUserId(userId);
		} else {
			enquiryList = enquiryMapper.selectEnquiryListByDivisionAndUserId(division, userId);
		}
		
		int num = enquiryList.size();
		
		Iterator<Enquiry> iter = enquiryList.iterator();
		
		while (iter.hasNext()) {
			Post post = new Post();
			
			Enquiry enquiry = iter.next();
			UserEntity user = userRepository.findById(enquiry.getUserId()).orElse(null);
			post.setNum(num--);
			post.setEnquiry(enquiry);
			post.setUserId(user.getLoginId());
			
			postList.add(post);
		}
		
		return postList;
	}

	public Post getPostById(int id) {
		Post post = new Post();
		
		Enquiry enquiry = enquiryMapper.selectEnquiryById(id);
		
		UserEntity user = userRepository.findById(enquiry.getUserId()).orElse(null);
		
		post.setUserId(user.getLoginId());
		post.setEnquiry(enquiry);
		return post;
	}

}
