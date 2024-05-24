package com.youniform.orderItem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.youniform.orderItem.domain.OrderItem;

@Mapper
public interface OrderItemMapper {

	public int insertOrderItem(
			@Param("orderId") int orderId,
			@Param("uniformId") Integer uniformId);

	public List<OrderItem> selectOrderItemListByOrderId(Integer userId);

	public void deleteOrderItemByOrderId(int orderId);

}
