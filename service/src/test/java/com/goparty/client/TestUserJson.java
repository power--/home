package com.goparty.client;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.ws.rs.core.MediaType;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestUserJson {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Test
	public void testGetEventList() throws Exception {
		String http = "http://goparty.cloudapp.net/cxf/rest/user/88/category/1?page=1&size=5";

		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(http);
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("GET");
			urlConnection.setUseCaches(false);
			urlConnection.setConnectTimeout(10000);
			urlConnection.setReadTimeout(10000);
			//urlConnection.setRequestProperty("Content-Type", "application/json");
			urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			urlConnection.setRequestProperty("Accept", "application/json");
			urlConnection.setRequestProperty("charset", "utf-8");
 

			int HttpResult = urlConnection.getResponseCode();
			StringBuffer sb = new StringBuffer();
			if (HttpResult == HttpURLConnection.HTTP_OK) {
				InputStream input = urlConnection.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(input, "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();

				System.out.println("" + sb.toString());

			} else {
				System.out.println(urlConnection.getResponseMessage());
			}
		}catch (Exception e) {
			logger.error("error",e);
		} finally {
			if (urlConnection != null)
				urlConnection.disconnect();
		}
	}
	
	
	@Test
	public void testJsonWithoutGZIP() throws Exception {
		String http = "http://goparty.cloudapp.net/cxf/rest/user";

		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(http);
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("POST");
			urlConnection.setUseCaches(false);
			urlConnection.setConnectTimeout(10000);
			urlConnection.setReadTimeout(10000);
			urlConnection.setRequestProperty("Content-Type", "application/json");
			urlConnection.setRequestProperty("Accept", "application/json");
			urlConnection.setRequestProperty("charset", "utf-8");

			String jsonStr = "{\"nickName\": \"Bo\", \"password\": \"password\",\"userName\": \"chenb\",\"QQ\": \"1343243\" }";
			urlConnection.setRequestProperty("Content-Length","" + jsonStr.getBytes("UTF-8").length);

			OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
			out.write(jsonStr);
			out.close();

			int HttpResult = urlConnection.getResponseCode();
			StringBuffer sb = new StringBuffer();
			if (HttpResult == HttpURLConnection.HTTP_OK) {
				InputStream input = urlConnection.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(input, "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();

				System.out.println("" + sb.toString());

			} else {
				System.out.println(urlConnection.getResponseMessage());
			}
		}catch (Exception e) {
			logger.error("error",e);
		} finally {
			if (urlConnection != null)
				urlConnection.disconnect();
		}
	}

	@Test
	public void testUserWithGZIP() throws Exception {
		String http = "http://goparty.cloudapp.net/cxf/rest/user";

		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(http);
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("POST");
			urlConnection.setUseCaches(false);
			urlConnection.setConnectTimeout(10000);
			urlConnection.setReadTimeout(10000);
			urlConnection.setRequestProperty("Content-Type", "application/json");
			urlConnection.setRequestProperty("Accept", "application/json");
			urlConnection.setRequestProperty("Accept-Encoding", "gzip");
			urlConnection.setRequestProperty("Content-Encoding", "gzip");

			urlConnection.setRequestProperty("charset", "utf-8");


			String jsonStr = "{\"nickName\": \"Bo\", \"password\": \"password\",\"userName\": \"chenb\" }";

			byte[] outBytes = toGzip(jsonStr);
			urlConnection.setRequestProperty("Content-Length", "" + outBytes.length);


			OutputStream out = urlConnection.getOutputStream();
			out.write(outBytes);
			out.close();

			int HttpResult = urlConnection.getResponseCode();
			StringBuffer sb = new StringBuffer();
			if (HttpResult == HttpURLConnection.HTTP_OK) {
				InputStream input = urlConnection.getInputStream();

				if ("gzip".equalsIgnoreCase(urlConnection.getContentEncoding())) {
					input = new GZIPInputStream(input);
				}			

				BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();

				System.out.println("" + sb.toString());

			} else {
				System.out.println(urlConnection.getResponseMessage());
			}
		} catch (Exception e) {
			logger.error("error",e);
		} finally {
			if (urlConnection != null)
				urlConnection.disconnect();
		}
	}

	private byte[] toGzip(String text) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			OutputStream out = new GZIPOutputStream(baos);
			out.write(text.getBytes("UTF-8"));
			out.close();
			return baos.toByteArray();
		} finally {
			baos.close();
		}
	}
}
