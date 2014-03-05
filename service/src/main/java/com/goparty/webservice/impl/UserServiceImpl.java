package com.goparty.webservice.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.common.util.StringUtils;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 














import com.goparty.data.model.Event;
import com.goparty.data.model.StringResponse;
import com.goparty.data.model.User; 
import com.goparty.data.model.UserFriend;
import com.goparty.data.model.UserToken;
import com.goparty.data.service.UserDataService;
import com.goparty.data.service.FriendDataService;
import com.goparty.exception.ValidationException;
import com.goparty.webservice.UserService;
import com.goparty.webservice.model.LoginRequest;


@Service("userService")
public class UserServiceImpl implements UserService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	String rootDir =  "D:/";
	
	@Autowired
	private UserDataService userDataService;
	
	@Autowired
	private FriendDataService userFriendDataService;

	@Override
	public User getUserInfo(String id) {
		User ret = userDataService.read(id); 
		return ret;
	}


	@Override
	public User getProfile(String token) {
		User ret = userDataService.getUserByToken(token);
		return ret;
	} 

	@Override
	public User updateProfile(User user) {		
		return userDataService.update(user);
	}

 

	public UserDataService getUserDataService() {
		return userDataService;
	}

	public void setUserDataService(UserDataService userDataService) {
		this.userDataService = userDataService;
	}

	@Override
	public StringResponse uploadImage(MultipartBody image,String userId) {	
		StringResponse response = new StringResponse();	
		MediaType contentType = image.getAllAttachments().get(0).getContentType();
		logger.info("content-type : " + contentType.getType());
		String extensionName = getExtensionName(contentType.toString());
		if(extensionName == null){
			response.setMessage("Don't support this type of image.");
			return response;
		}
		String fileName = String.valueOf(System.currentTimeMillis()) + extensionName;		
		DataHandler handler = image.getAllAttachments().get(0).getDataHandler();
		try {
			InputStream is =handler.getInputStream();
			FileOutputStream fos = new FileOutputStream( rootDir + fileName);
			byte[] buffer = new byte[1024];
			int size = 0;
			while((size = is.read(buffer))!=-1){
				fos.write(buffer,0,size);
			}
			is.close();
			fos.close();
			
		} catch (IOException e) {
			logger.error("upload image error!"); 
			e.printStackTrace();
		}
	
		User user = new User();
		user.setId(userId);
		user.setPhoto(fileName);
		userDataService.update(user);
		logger.info("upload image successfully,filename:" + fileName); 		 
		response.setMessage(fileName);
		return response;
	}
	
	
	private String getExtensionName(String contentType){ 
		if(contentType.indexOf("image/gif")!=-1){
			return ".gif";
		}else if(contentType.indexOf("image/png")!=-1){
			return ".png";
		}else if(contentType.indexOf("image/jpeg")!=-1){
			return ".jpeg";
		}else if(contentType.indexOf("image/x-icon")!=-1){
			return ".ico";
		}
		return null;
	}

	 
  


	@Override
	public User login(String token, LoginRequest request){
		if(StringUtils.isEmpty(token)){
			String openId = request.getOpenId();
			String tokenId = request.getTokenId();
			//validation using openId and tokenId, then get the user info by API
			//validation...
			String loginId = openId;
			String nickName = tokenId;  //get by API  
			
			User user = userDataService.getUserByLoginId(loginId);
			if(user == null){
				User newGuy = new User();
				newGuy.setLoginId(loginId);	
				newGuy.setNickName(nickName);
				user = userDataService.create(newGuy);				
			}else{
				//existed this user in our system				
			} 
			UserToken ut = userDataService.generateToken(user.getId(),30);//expired the token after one month
			token = ut.getToken();
		}else{
			//validate token
			UserToken ut = userDataService.getUserToken(token);
			Date curDate = new Date();
			if(ut != null && curDate.after(ut.getExpireTime())){
				Map<String, String> data = new HashMap();
				data.put("1", "Token has expired");
				throw new ValidationException(data);
			}
		}
		User u = userDataService.getUserByToken(token);
		logger.info("Login successfully. token=" + token + ", userId=" + u.getId());
		return u;
	}


	@Override
	public boolean logout(String token) {
		UserToken ut = userDataService.getUserToken(token);
		if(ut != null){
			userDataService.generateToken(ut.getUserId(),-1);
		}
		return true;
	}


	@Override
	public List<User> search(String keyword, int offset, int limit) {
		return userDataService.search(keyword, offset, limit);
	}

 
	
}
