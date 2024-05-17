package com.youniform.basket.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youniform.basket.mapper.BasketMapper;

@Service
public class BasketBO {
	@Autowired
	private BasketMapper basketMapper;
	
}
