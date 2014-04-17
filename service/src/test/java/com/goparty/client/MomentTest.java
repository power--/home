package com.goparty.client;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.goparty.data.constant.EventVisibility;
import com.goparty.webservice.model.MomentRequest;
import com.goparty.webservice.model.PhotoInfo;

public class MomentTest {
	
	@Test
	public void testJson() throws Exception{
		MomentRequest req = new MomentRequest();
		req.setMoment("Hello　World");
		req.setVisibility(EventVisibility.V_PUBLIC.toString());
		
		PhotoInfo p = new PhotoInfo();
		p.setNickName("hi");
		
		
		byte[] bytes = Files.readAllBytes(Paths.get("e:/P30317-180946.JPG"));
		p.setPhoto(Base64.encodeBase64String(bytes));
		req.getPhotos().add(p);
		
		ObjectMapper mapper = new ObjectMapper();
		
		
		String data = mapper.writeValueAsString(req);
		
		
		String ret = null;

		HttpURLConnection urlConnection = null;
		try {
			URL u = new URL("http://goparty.cloudapp.net/cxf/rest/mevents/10/moments");
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
			urlConnection.setRequestProperty("token", "98326219-4388-4692-a93f-f18b2c7a275e");
			urlConnection.setRequestProperty("Accept-Encoding", "gzip");
			urlConnection.setRequestProperty("Content-Encoding", "gzip");
			
			byte[] outBytes = toGzip(data);
			urlConnection.setRequestProperty("Content-Length", "" + outBytes.length);

			OutputStream out = urlConnection.getOutputStream();
			out.write(outBytes);
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
