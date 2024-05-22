package com.youniform.basket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.youniform.basket.domain.Basket;

@Mapper
public interface BasketMapper {

	public int insertBasket(
			@Param("userId") int userId,
			@Param("uniformId") int uniformId);

	public List<Basket> selectBasketListByUserId(int userId);

	public void deleteBasketByUniformId(int uniformId);

	public boolean selectBasketByUserIdAndUniformId(
			@Param("userId") int userId,
			@Param("uniformId") int uniformId);

	public boolean isBasketEmpty(Integer userId);
	
}
