package com.youniform.uniform.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.youniform.basket.domain.Basket;
import com.youniform.basket.mapper.BasketMapper;
import com.youniform.common.FileManagerService;
import com.youniform.orderItem.domain.OrderItem;
import com.youniform.orderItem.mapper.OrderItemMapper;
import com.youniform.uniform.domain.Uniform;
import com.youniform.uniform.mapper.UniformMapper;

@Service
public class UniformBO {
	@Autowired
	private UniformMapper uniformMapper;
	
	@Autowired
	private BasketMapper basketMapper;
	
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	@Autowired
	private FileManagerService fileManager; 
	
	public List<Uniform> getUniformList() {
		return uniformMapper.selectUniformList();
	}

	public Uniform getUniformById(int id) {
		return uniformMapper.selectUniformById(id);
	}

	public List<Uniform> getUniformListByBasket(int userId) {
		List<Basket> basketList = basketMapper.selectBasketListByUserId(userId);
		List<Uniform> uniformList = new ArrayList<>();
		
		Iterator<Basket> iter = basketList.iterator();
		
		while (iter.hasNext()) {
			Basket basket = iter.next();
			
			Uniform uniform = uniformMapper.selectUniformById(basket.getUniformId());
			
			uniformList.add(uniform);
		}
		
		return uniformList;
	}

	public void deleteUniformById(int id) {
		Uniform uniform = uniformMapper.selectUniformById(id);
		
		fileManager.deleteFile(uniform.getImage());
		uniformMapper.deleteUniformById(id);
		basketMapper.deleteBasketByUniformId(id);
	}

	public int addUniform(Integer userId, String loginId, String league, String subject, int price,
			String description, MultipartFile file, String status) {
		String image = fileManager.saveFile(loginId, file);
		String confirm = null;
		
		if (userId == 1) {
			confirm = "Y";
		} else {
			confirm = "N";
		}
		
		return uniformMapper.insertUniform(userId, league, subject, description, image, price, status, confirm);
	}

	public List<Uniform> getUniformListById(int id) {
		List<OrderItem> orderItemList = orderItemMapper.selectOrderItemListByOrderId(id);
		
		List<Uniform> uniformList = new ArrayList<>();
		
		Iterator<OrderItem> iter = orderItemList.iterator();
		
		while (iter.hasNext()) {
			OrderItem data = iter.next();
			
			Uniform uniform = uniformMapper.selectUniformById(data.getUniformId());
			
			uniformList.add(uniform);
		}
		return uniformList;
	}

	public void updateUniformById(int id, String league, String subject, String description, int price, String status) {
		String confirm = "Y";
		uniformMapper.updateUniformById(id, league, subject, description, price, status, confirm);
	}

	public List<Uniform> getUniformListBySearch(String keyword) {
		List<Uniform> uniformList = uniformMapper.selectUniformList();
		
		String lowerCaseKeyword = keyword.toLowerCase();
		
		List<Uniform> filteredList = uniformList.stream()
	            .filter(uniform -> uniform.getSubject() != null &&
	                               uniform.getSubject().toLowerCase().contains(lowerCaseKeyword))
	            .collect(Collectors.toList());

		return filteredList;
	}

	public List<Uniform> getUniformListByLeague(String league) {
		return uniformMapper.selectUniformListByLeague(league);
	}

}
