package com.goparty.webservice.impl;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goparty.data.dao.EventCategoryDao;
import com.goparty.webservice.EventCategoryService;
import com.goparty.webservice.utils.ResponseUtil;

@Service("eventCategoryService")
public class EventCategoryServiceImpl implements EventCategoryService {
	@Autowired
	private EventCategoryDao eventCategoryDao;

	@Override
	public Response list() {
		return ResponseUtil.buildResponse(eventCategoryDao.list());
	}
}
