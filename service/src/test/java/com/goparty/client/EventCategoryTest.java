package com.goparty.client;

import org.junit.Test;

public class EventCategoryTest {
	
	@Test
	public void test() throws Exception{
		String url = "http://goparty.cloudapp.net/cxf/rest/eventcategories";
		
		HttpClientUtils.get(url);
	}
}
