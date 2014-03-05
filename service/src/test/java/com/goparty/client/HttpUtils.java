package com.goparty.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
	
	private  String token;
	
	public HttpUtils(String token){
		this.token = token;
	}
	
	public  String deleteData(String url) throws Exception {
		String ret = null;

		HttpURLConnection conn = null;
		try {
			URL obj = new URL(url);
			conn = (HttpURLConnection) obj.openConnection();

			conn.setRequestMethod("DELETE");
			conn.setRequestProperty("Accept", "application/json");
			
			// add request header
			conn.setRequestProperty("token", token);
			
			int responseCode = conn.getResponseCode();
			System.out.println("\nSending 'DELETE' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			ret = response.toString();

		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		return ret;
	}

	public   String getData(String url) throws Exception {
		String ret = null;

		HttpURLConnection conn = null;
		try {
			URL obj = new URL(url);
			conn = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			// add request header
			conn.setRequestProperty("token", token);
			
			int responseCode = conn.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			ret = response.toString();

		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		return ret;
	}

	public   String postData(String url,String json) throws Exception {
		return sendData("POST",json,url);
	}
	public   String putData(String url,String json) throws Exception {
		return sendData("PUT",json,url);
	}
	
	private   String sendData(String method, String json, String siteUrl) {
		String ret = null; 
		HttpURLConnection conn = null;
		try {
			URL url = new URL(siteUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod(method);
			conn.setUseCaches(false);
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("charset", "utf-8");
			
			// add request header
			conn.setRequestProperty("token", token);
			
			conn.setRequestProperty("Content-Length","" + json.getBytes("UTF-8").length);

			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
			out.write(json);
			out.close();

			int HttpResult = conn.getResponseCode();
			StringBuffer sb = new StringBuffer();
			if (HttpResult == HttpURLConnection.HTTP_OK) {
				InputStream input = conn.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(input, "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				ret = sb.toString();
			} else {
				System.out.println(conn.getResponseMessage());
			}
		} catch (Exception e) { 
		} finally {
			if (conn != null)
				conn.disconnect();
		}
		return ret;
	}
}
