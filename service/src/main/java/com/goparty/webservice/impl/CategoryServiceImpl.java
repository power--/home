package com.goparty.webservice.impl;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goparty.data.dao.CategoryDao;
import com.goparty.webservice.CategoryService;
import com.goparty.webservice.utils.ResponseUtil;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public Response list() {
		return ResponseUtil.buildResponse(categoryDao.list());
	}
}
