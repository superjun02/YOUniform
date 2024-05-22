package com.youniform.basket.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youniform.basket.domain.Basket;
import com.youniform.basket.mapper.BasketMapper;
import com.youniform.uniform.domain.Uniform;
import com.youniform.uniform.mapper.UniformMapper;

@Service
public class BasketBO {
	@Autowired
	private BasketMapper basketMapper;
	
	@Autowired
	private UniformMapper uniformMapper;
	
	public int addBasket(int userId, int uniformId) {
		return basketMapper.insertBasket(userId, uniformId);
	}

	public void deleteBasketByUniformId(int uniformId) {
		basketMapper.deleteBasketByUniformId(uniformId);
	}

	public boolean getBasketByUserIdAndUniformId(int userId, int uniformId) {
		return basketMapper.selectBasketByUserIdAndUniformId(userId, uniformId);
	}

	public boolean isBasketEmpty(Integer userId) {
		return basketMapper.isBasketEmpty(userId);
	}

	public List<Uniform> getBasketByUserId(Integer userId) {
		List<Uniform> uniforms = new ArrayList<>();
		
		List<Basket> basket = basketMapper.selectBasketListByUserId(userId);
		
		Iterator<Basket> iter = basket.iterator();
		
		while(iter.hasNext()) {
			Basket data = iter.next();
			
			Uniform uniform = uniformMapper.selectUniformById(data.getUniformId());
			
			uniforms.add(uniform);
		}
		return uniforms;
	}
	
}
