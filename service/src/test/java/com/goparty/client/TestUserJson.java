package com.goparty.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

public class TestUserJson {
	
	@Test
	public void testUser(){
		String http = "http://goparty.cloudapp.net/cxf/rest/user";  


		HttpURLConnection urlConnection=null;  
		try {  
		    URL url = new URL(http);  
		    urlConnection = (HttpURLConnection) url.openConnection();
		    urlConnection.setDoOutput(true);   
		    urlConnection.setRequestMethod("POST");  
		    urlConnection.setUseCaches(false);  
		    urlConnection.setConnectTimeout(10000);  
		    urlConnection.setReadTimeout(10000);  
		    urlConnection.setRequestProperty("Content-Type","application/json"); 
		    urlConnection.setRequestProperty("Accept", "application/json");


		    urlConnection.setRequestProperty("charset", "utf-8");

		    //Create JSONObject here
//		    String jsonStr = "{\"user\": {\"nickName\": \"Bo\", \"password\": \"password\",\"userName\": \"chenb\" }}";
		    String jsonStr = "{\"nickName\": \"Bo\", \"password\": \"password\",\"userName\": \"chenb\" }";
		    
		    
		    
		    urlConnection.setRequestProperty("Content-Length", "" + jsonStr.getBytes("UTF-8").length);
		    
		    OutputStreamWriter out = new   OutputStreamWriter(urlConnection.getOutputStream());
		    out.write(jsonStr);
		    out.close();  

		    int HttpResult =urlConnection.getResponseCode(); 
		    StringBuffer sb = new StringBuffer();
		    if(HttpResult ==HttpURLConnection.HTTP_OK){  
		        BufferedReader br = new BufferedReader(new InputStreamReader(  
		            urlConnection.getInputStream(),"utf-8"));  
		        String line = null;  
		        while ((line = br.readLine()) != null) {  
		            sb.append(line + "\n");  
		        }  
		        br.close();  

		        System.out.println(""+sb.toString());  

		    }else{  
		            System.out.println(urlConnection.getResponseMessage());  
		    }  
		} catch (MalformedURLException e) {  

		         e.printStackTrace();  
		}  
		catch (IOException e) {  
		    e.printStackTrace();
		}finally{  
		    if(urlConnection!=null)  
		    urlConnection.disconnect();  
		}  
	}
}
