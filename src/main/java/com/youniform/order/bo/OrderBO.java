package com.youniform.order.bo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youniform.basket.domain.Basket;
import com.youniform.basket.mapper.BasketMapper;
import com.youniform.order.domain.Order;
import com.youniform.order.mapper.OrderMapper;
import com.youniform.orderItem.mapper.OrderItemMapper;

@Service
public class OrderBO {
	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private OrderItemMapper orderItemMapper;
	
	@Autowired
	private BasketMapper basketMapper;
	public int addOrder(int userId, String address, Integer uniformId, int totalPrice) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String currentTimeStr = now.format(formatter);
		String orderNumber = currentTimeStr + Integer.toString(userId);
		String payment = "무통장입금";

		if (uniformId != null) {

			orderMapper.insertOrder(userId, orderNumber, payment, totalPrice, address);
			int orderId = orderMapper.selectOrderByOrderNumber(orderNumber).getId();
			return orderItemMapper.insertOrderItem(orderId, uniformId);
			
		}
		else {
			orderMapper.insertOrder(userId, orderNumber, payment, totalPrice, address);
			int orderId = orderMapper.selectOrderByOrderNumber(orderNumber).getId();
			
			List<Basket> basketList = basketMapper.selectBasketListByUserId(userId);
			
			Iterator<Basket> iter = basketList.iterator();
			
			while (iter.hasNext()) {
				Basket basket = iter.next();
				orderItemMapper.insertOrderItem(orderId, basket.getUniformId());
				basketMapper.deleteBasketByUniformId(basket.getUniformId());
			}
			return 1;
		}
	}
	public void deleteOrderByOrderId(int orderId) {
		orderMapper.deleteOrderByOrderId(orderId);
	}
	public List<Order> getOrderListByUserId(Integer userId) {
		return orderMapper.selectOrderByUserId(userId);
	}
}
