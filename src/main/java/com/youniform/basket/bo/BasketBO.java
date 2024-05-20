package com.youniform.basket.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youniform.basket.mapper.BasketMapper;

@Service
public class BasketBO {
	@Autowired
	private BasketMapper basketMapper;

	public int addBasket(int userId, int uniformId) {
		return basketMapper.insertBasket(userId, uniformId);
	}

	public void deleteBasketByUniformId(int uniformId) {
		basketMapper.deleteBasketByUniformId(uniformId);
	}

	public boolean getBasketByUserIdAndUniformId(int userId, int uniformId) {
		return basketMapper.selectBasketByUserIdAndUniformId(userId, uniformId);
	}
	
}
