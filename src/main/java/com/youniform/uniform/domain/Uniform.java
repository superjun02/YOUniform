package com.youniform.uniform.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Uniform {
	private int id;
	private int userId;
	private String league;
	private String subject;
	private String description;
	private String image;
	private int price;
	private String status;
	private String confirm;
	private Date createdAt;
	private Date updatedAt;
}
