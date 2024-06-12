package com.youniform.common;

import org.springframework.stereotype.Service;

@Service
public class DataSizeChecker {

	public Integer getTotalPages(int totalItems, int pageSize) {
		Integer totalPages = totalItems / pageSize;

		if (totalItems % pageSize != 0) {
			totalPages++;
		}
		
		return totalPages;
	}

}
