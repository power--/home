package com.goparty.data.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.model.Category;
import com.goparty.data.repository.ICategoryRepository;

@Repository("categoryDao")
@Transactional
public class CategoryDao {
	private Log log = LogFactory.getLog(CategoryDao.class);

	@Autowired
	private ICategoryRepository categoryRepository;
	
	public List<Category> list(){
		return categoryRepository.findAll();
	}
}
