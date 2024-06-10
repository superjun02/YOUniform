package com.youniform.enquiry.domain;

import lombok.Data;

@Data
public class Post {
	private String userId;
	private Enquiry enquiry;
}
