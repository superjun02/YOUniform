package com.youniform.orderItem.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youniform.orderItem.mapper.OrderItemMapper;

@Service
public class OrderItemBO {
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	public int addOrderItem(int orderId, Integer uniformId) {
		return orderItemMapper.insertOrderItem(orderId, uniformId);
	}

}
