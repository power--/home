package com.goparty.web.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login/qq")
public class QQController {
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody String execute(ModelMap model, HttpServletRequest request,
		   @RequestParam("access_token") String accessToken,  
		   @RequestParam("oauth_consumer_key") String oauthConsumerKey, 
		   @RequestParam("openid") String openid
			){
		String qqURL = "https://graph.qq.com/user/get_user_info?access_token=%s&oauth_consumer_key=%s&openid=%s";
		
		System.out.println(String.format(qqURL, accessToken,oauthConsumerKey, openid));
		
		
		String ret = null;
		String url ="http://localhost/oauth/token?client_id=mobile-client&client_secret=mobile&grant_type=password&scope=read,write&username=admin&password=admin";

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

		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		
		return ret;
	}
}
