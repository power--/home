package com.goparty.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

public class MyEventTest {
	@Test
	public void test() throws Exception{
		String url = "http://goparty.cloudapp.net/cxf/rest/myevents";
		System.out.println("GET");
		System.out.println(url);
		String ret = null;

		HttpURLConnection conn = null;
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("token", "5397efef-01ef-4d4b-aef8-260508af81bf");
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			ret = response.toString();

		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		System.out.println("Response");
		System.out.println(ret);
		System.out.println("-------------END------------------------");
	}
}
