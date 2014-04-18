package com.goparty.webservice.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.activation.DataHandler;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.common.util.StringUtils; 
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 











import com.goparty.data.dao.FriendDao;
import com.goparty.data.dao.UserDao;
import com.goparty.data.exception.BaseException;
import com.goparty.data.model.StringResponse;
import com.goparty.data.model.User;  
import com.goparty.data.model.UserToken;
import com.goparty.exception.ValidationException;
import com.goparty.webservice.UserService; 
import com.goparty.webservice.model.LoginRequest; 
import com.goparty.webservice.model.UserRequest;
import com.goparty.webservice.model.UserResponse;
import com.goparty.webservice.utils.ResponseUtil;


@Service("userService")
public class UserServiceImpl implements UserService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	String rootDir =  "D:/";
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private FriendDao friendDao;

	@Override
	public Response getUserInfo(String id) {		
		User ret = userDao.read(id);  
		return ResponseUtil.buildResponse(getUserResponse(ret));
	}
	

	//ignore null values
	private String[] getNullPropertyNames (Object source) {
	    final BeanWrapper src = new BeanWrapperImpl(source);
	    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

	    Set<String> emptyNames = new HashSet<String>();
	    for(java.beans.PropertyDescriptor pd : pds) {
	        Object srcValue = src.getPropertyValue(pd.getName());
	        if (srcValue == null) emptyNames.add(pd.getName());
	    }
	    String[] result = new String[emptyNames.size()];
	    return emptyNames.toArray(result);
	}
	private UserResponse getUserResponse(User user){
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(user, userResponse);
		return userResponse;
	}
	@Override
	public Response getProfile(String token) {
		User ret = userDao.getUserByToken(token);
		return ResponseUtil.buildResponse(getUserResponse(ret));
	} 

	@Override
	public Response updateProfile(String token,UserRequest request) {
		User currentUser = userDao.getUserByToken(token); 
		BeanUtils.copyProperties(request, currentUser,getNullPropertyNames(request));
		return ResponseUtil.buildResponse(getUserResponse(userDao.update(currentUser)));
	} 

	public UserDao getUserDataService() {
		return userDao;
	}

	public void setUserDataService(UserDao userDataService) {
		this.userDao = userDataService;
	}

	@Override
	public Response uploadImage(MultipartBody image,String userId) {	
		StringResponse response = new StringResponse();	
		MediaType contentType = image.getAllAttachments().get(0).getContentType();
		logger.info("content-type : " + contentType.getType());
		String extensionName = getExtensionName(contentType.toString());
		if(extensionName == null){
			response.setMessage("Don't support this type of image.");
			return ResponseUtil.buildResponse(response);
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
		userDao.update(user);
		logger.info("upload image successfully,filename:" + fileName); 		 
		response.setMessage(fileName);
		return ResponseUtil.buildResponse(response);
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
	public Response login(String token, LoginRequest request){
		if(StringUtils.isEmpty(token)){
			String openId = request.getOpenId();
			String tokenId = request.getTokenId();
			String mobile = request.getMobile();
			//validation using openId and tokenId, then get the user info by API
			//validation...
			String loginId = mobile;//openId;
			String nickName = mobile; //tokenId;  //get by API  
			
			User user = userDao.getUserByLoginId(loginId);
			if(user == null){
				User newGuy = new User();
				newGuy.setLoginId(loginId);	
				newGuy.setNickName(nickName);
				user = userDao.create(newGuy);				
			}else{
				//existed this user in our system				
			} 
			UserToken ut = userDao.generateToken(user.getId(),30);//expired the token after one month
			token = ut.getToken();
		}else{
			//validate token
			UserToken ut = userDao.getUserToken(token);
			Date curDate = new Date();
			if(ut != null && curDate.after(ut.getExpireTime())){ 
				throw new BaseException("Token has expired");
			}
		}
		User u = userDao.getUserByToken(token);
		if(u==null){
			throw new BaseException("User not exist!");
		}
		logger.info("Login successfully. token=" + token + ", userId=" + u.getId());
		UserResponse response = getUserResponse(u);		
		 
		return ResponseUtil.buildResponse(response,u.getToken());
	}

	@Override
	public Response logout(String token) {
		UserToken ut = userDao.getUserToken(token);
		if(ut != null){
			userDao.generateToken(ut.getUserId(),-1);
		}
		return ResponseUtil.buildResponse(true);
	}


	@Override
	public Response search(String keyword, int offset, int limit) {
		List<UserResponse> urList = new ArrayList<UserResponse>();
		for(User user : userDao.search(keyword, offset, limit)){ 
			urList.add(this.getUserResponse(user));
		}
		return ResponseUtil.buildResponse(urList);
	}

 
	
}
