package com.goparty.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClientUtils {
	public static String get(String url) throws Exception{
		System.out.println("GET");
		System.out.println(url);
		String ret = null;

		HttpURLConnection conn = null;
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");


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
		return ret;
	}
	
	public static String post(String url, String data) throws Exception{
		System.out.println("POST");
		System.out.println(url);
		System.out.println(data);
		
		String ret = null;

		HttpURLConnection urlConnection = null;
		try {
			URL u = new URL(url);
			urlConnection = (HttpURLConnection) u.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("POST");
			urlConnection.setUseCaches(false);
			urlConnection.setConnectTimeout(10000);
			urlConnection.setReadTimeout(10000);
			urlConnection
					.setRequestProperty("Content-Type", "application/json");
			urlConnection.setRequestProperty("Accept", "application/json");
			urlConnection.setRequestProperty("charset", "utf-8");

			urlConnection.setRequestProperty("Content-Length",
					"" + data.getBytes("UTF-8").length);

			OutputStreamWriter out = new OutputStreamWriter(
					urlConnection.getOutputStream());
			out.write(data);
			out.close();

			int HttpResult = urlConnection.getResponseCode();
			
			System.out.println(urlConnection.getResponseMessage());
			
			StringBuffer sb = new StringBuffer();
			if (HttpResult == HttpURLConnection.HTTP_OK) {
				InputStream input = urlConnection.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						input, "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				ret = sb.toString();
			} else {
				InputStream input = urlConnection.getErrorStream();
				if(input !=null){
					BufferedReader br = new BufferedReader(new InputStreamReader(
							input, "utf-8"));
					String line = null;
					while ((line = br.readLine()) != null) {
						sb.append(line + "\n");
					}
					br.close();
					ret = sb.toString();
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (urlConnection != null)
				urlConnection.disconnect();
		}
		
		System.out.println("Response");
		System.out.println(ret);
		System.out.println("-------------END------------------------");
		
		return ret;
	}
	
	public static String put(String url, String data) throws Exception{
		System.out.println("PUT");
		System.out.println(url);
		System.out.println(data);
		
		String ret = null;

		HttpURLConnection urlConnection = null;
		try {
			URL u = new URL(url);
			urlConnection = (HttpURLConnection) u.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("PUT");
			urlConnection.setUseCaches(false);
			urlConnection.setConnectTimeout(10000);
			urlConnection.setReadTimeout(10000);
			urlConnection
					.setRequestProperty("Content-Type", "application/json");
			urlConnection.setRequestProperty("Accept", "application/json");
			urlConnection.setRequestProperty("charset", "utf-8");

			urlConnection.setRequestProperty("Content-Length",
					"" + data.getBytes("UTF-8").length);

			OutputStreamWriter out = new OutputStreamWriter(
					urlConnection.getOutputStream());
			out.write(data);
			out.close();

			int HttpResult = urlConnection.getResponseCode();
			
			System.out.println(urlConnection.getResponseMessage());
			
			StringBuffer sb = new StringBuffer();
			if (HttpResult == HttpURLConnection.HTTP_OK) {
				InputStream input = urlConnection.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						input, "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				ret = sb.toString();
			} else {
				InputStream input = urlConnection.getErrorStream();
				if(input !=null){
					BufferedReader br = new BufferedReader(new InputStreamReader(
							input, "utf-8"));
					String line = null;
					while ((line = br.readLine()) != null) {
						sb.append(line + "\n");
					}
					br.close();
					ret = sb.toString();
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (urlConnection != null)
				urlConnection.disconnect();
		}
		
		System.out.println("Response");
		System.out.println(ret);
		System.out.println("-------------END------------------------");
		return ret;
	}
	
	public static String delete(String url) throws Exception{
		System.out.println("DELETE");
		System.out.println(url);
		
		String ret = null;

		HttpURLConnection conn = null;
		try {
			URL u = new URL(url);
			HttpURLConnection con = (HttpURLConnection) u.openConnection();

			con.setRequestMethod("DELETE");
			con.setRequestProperty("Accept", "application/json");

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'DELETE' request to URL : " + url);
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

		return ret;
	}
}
