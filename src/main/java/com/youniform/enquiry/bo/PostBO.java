package com.youniform.enquiry.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
	
	public PageInfo<Post> getPostListByUserId(String division, Integer userId, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        
        List<Enquiry> enquiryList = enquiryMapper.selectEnquiryListByDivisionAndUserId(division, userId);
        
        List<Post> postList = new ArrayList<>();
        
        for (Enquiry enquiry : enquiryList) {
            Post post = new Post();
            
            UserEntity user = userRepository.findById(enquiry.getUserId()).orElse(null);
            post.setEnquiry(enquiry);
            post.setUserId(user != null ? user.getLoginId() : null);
            
            postList.add(post);
        }
        
        PageInfo<Post> pageInfo = new PageInfo<>(postList);
        return pageInfo;
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
