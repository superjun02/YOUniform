package com.youniform.orderItem.domain;

import java.util.Date;

import lombok.Data;

@Data
public class OrderItem {
	private int orderId;
	private int uniformId;
	private Date createdAt;
}
