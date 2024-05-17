package com.youniform.orderItem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderItemMapper {

	public int insertOrderItem(
			@Param("orderId") int orderId,
			@Param("uniformId") Integer uniformId);

}
