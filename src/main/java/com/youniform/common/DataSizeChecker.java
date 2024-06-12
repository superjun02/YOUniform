package com.youniform.common;

import org.springframework.stereotype.Service;

@Service
public class DataSizeChecker {

	public Integer getTotalPages(int totalItems, int pageSize) {
		return totalItems / pageSize;
	}
	
}
