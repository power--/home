package com.goparty.webservice.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.goparty.data.dao.UserDao;
import com.goparty.data.model.User;
import com.goparty.webservice.MomentService;
import com.goparty.webservice.model.MomentRequest;
import com.goparty.webservice.model.PhotoRequest;
import com.goparty.webservice.utils.ImageFormatUtil;

public class MomentServiceImpl implements MomentService {
	private Log log = LogFactory.getLog(MomentServiceImpl.class);
	
	private String baseDir = "c:/photo";
	@Autowired
	private UserDao userDao;

	@Override
	public Response create(String eventId, String token, MomentRequest request) {
		User currentUser = userDao.getUserByToken(token);
		
		List<PhotoRequest> photos = request.getPhotos();
		
		File root = new File(baseDir);
		File userFolder = new File(root, currentUser.getId());
		if(!userFolder.exists()){
			userFolder.mkdir();
		}
		
		File eventFolder = new File(userFolder, eventId);
		if(!eventFolder.exists()){
			eventFolder.mkdir();
		}
		
		for(PhotoRequest p:photos){
			byte[] decodedBytes = Base64.decodeBase64(p.getPhoto());
			
			String format = ImageFormatUtil.parseFormat(decodedBytes);
			if(format.equals(ImageFormatUtil.BAD_FORMAT)){
				//TODO
			}else{
				String id = java.util.UUID.randomUUID().toString();
				File f = new File(eventFolder, id+"."+format);
				try(FileOutputStream fos = new FileOutputStream(f)){
			
					fos.write(decodedBytes);
					
				}catch(Exception ex){
					log.error(ex);
				}
			}

		}
		
		return null;
	}
	
	

}
