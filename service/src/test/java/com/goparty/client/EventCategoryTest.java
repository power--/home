package com.goparty.client;

import org.junit.Test;

public class EventCategoryTest {
	
	@Test
	public void test() throws Exception{
		String url = "http://localhost:8080/cxf/rest/eventcategories";
		
		HttpClientUtils.get(url);
	}
}
