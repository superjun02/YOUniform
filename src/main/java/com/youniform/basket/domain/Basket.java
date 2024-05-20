package com.youniform.basket.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Basket {
	private int id;
	private int userId;
	private int uniformId;
	private Date createdAt;
}
