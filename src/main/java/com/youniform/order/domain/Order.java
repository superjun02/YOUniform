package com.youniform.order.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Order {
	private int id;
	private int userId;
	private String orderNumber;
	private String payment;
	private int totalPrice;
	private String address;
	private String status;
	private Date createdAt;
	private Date updatedAt;
}
