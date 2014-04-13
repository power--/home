package com.goparty.client;

import org.junit.Test;

public class FullTextSearchTest {
	@Test
	public void test() throws Exception{
		String url = "http://goparty.cloudapp.net/cxf/rest/indexdata";
		HttpClientUtils.get(url);
	}
}
