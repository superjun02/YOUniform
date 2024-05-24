package com.youniform.uniform.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.youniform.basket.domain.Basket;
import com.youniform.basket.mapper.BasketMapper;
import com.youniform.common.FileManagerService;
import com.youniform.uniform.domain.Uniform;
import com.youniform.uniform.mapper.UniformMapper;

@Service
public class UniformBO {
	@Autowired
	private UniformMapper uniformMapper;
	
	@Autowired
	private BasketMapper basketMapper;
	
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
			String description, MultipartFile file) {
		String image = fileManager.saveFile(loginId, file);
		
		return uniformMapper.insertUniform(userId, league, subject, description, image, price);
	}

}
