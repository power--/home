package com.goparty.webservice.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.activation.DataHandler;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goparty.biz.model.Event;
import com.goparty.biz.model.StringResponse;
import com.goparty.biz.model.User;
import com.goparty.data.service.UserDataService;
import com.goparty.webservice.UserService;


@Service("userService")
public class UserServiceImpl implements UserService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	String rootDir =  "D:/";
	
	@Autowired
	private UserDataService userDataService;

	@Override
	public User read(String id) {
		User ret = userDataService.read(id);
		if(ret==null){
			logger.error("NULLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
		}
		return ret;
	}

	@Override
	public User create(User user) {
		userDataService.create(user);
		return user;
	}

	@Override
	public User update(User user) {
		userDataService.update(user);
		return user;
	}

	@Override
	public boolean delete(String id) {
		boolean ret = false;
		try{
			userDataService.delete(id);
			ret = true;
		}catch(Exception ex){
			logger.error("del user error",ex);
			throw ex;
		}
		
		return ret;
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
	public List<Event> events(String userId, int offset, int limit, String range,
			String filter, String sort) {
		
		if(offset==0&&limit==0){
			if(range!=null){
				String[] r = range.split("=")[1].split("-");
				offset = new Integer(r[0]);
				limit = new Integer(r[1])-offset;
			}
		}
		
		
		
		return null;
	}
	
	
}
