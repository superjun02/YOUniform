package com.youniform.order.bo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youniform.order.mapper.OrderMapper;
import com.youniform.orderItem.bo.OrderItemBO;

@Service
public class OrderBO {
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderItemBO orderItemBO;
	
	public int addOrder(int userId, String address, Integer uniformId, int totalPrice) {
		if (uniformId != null) {
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
			String currentTimeStr = now.format(formatter);
			String orderNumber = currentTimeStr + Integer.toString(userId);
			String payment = "무통장입금";
			
			if (orderMapper.insertOrder(userId, orderNumber, payment, totalPrice, address) == 1) {
				int orderId = orderMapper.selectOrderByOrderNumber(orderNumber).getId();
				return orderItemBO.addOrderItem(orderId, uniformId);
			}
		}
		return 0;
	}

}
