package com.youniform.enquiry.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Enquiry {
	private int id;
	private int userId;
	private String division;
	private String subject;
	private String description;
	private String answer;
	private Date createdAt;
	private Date updatedAt;
}
