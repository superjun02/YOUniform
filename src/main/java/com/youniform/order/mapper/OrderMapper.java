package com.youniform.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.youniform.order.domain.Order;

@Mapper
public interface OrderMapper {
	
	public int insertOrder(
			@Param("userId") int userId,
			@Param("orderNumber") String orderNumber,
			@Param("payment") String payment,
			@Param("totalPrice") int totalPrice,
			@Param("address") String address);

	public Order selectOrderByOrderNumber(String orderNumber);

	public List<Order> selectOrderListByUserId(Integer userId);
	
	public void deleteOrderByOrderId(int orderId);

	public Order selectOrderById(int id);

	public void updateOrderByIdAndStatus(
			@Param("id") int id,
			@Param("status") String status);
}
