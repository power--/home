package com.goparty.webservice.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.dao.UserDao;
import com.goparty.data.model.Event;
import com.goparty.data.model.Moment;
import com.goparty.data.model.Photo;
import com.goparty.data.model.User;
import com.goparty.data.repository.IMomentRepository;
import com.goparty.photo.PhotoStore;
import com.goparty.webservice.MomentService;
import com.goparty.webservice.model.MomentRepsone;
import com.goparty.webservice.model.MomentRequest;
import com.goparty.webservice.model.PhotoInfo;
import com.goparty.webservice.utils.ResponseUtil;

@Service("momentService")
public class MomentServiceImpl implements MomentService {
	private Log log = LogFactory.getLog(MomentServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PhotoStore photoStore;

	@Autowired
	private IMomentRepository momentRepository;
	
	@Override
	@Transactional
	public Response create(String eventId, String token, MomentRequest request) {
		
		Moment model = new Moment();
		model.setId(UUID.randomUUID().toString());
		
		User currentUser = userDao.getUserByToken(token);
		Event evt = new Event();
		evt.setId(eventId);
		model.setEvent(evt);
		model.setUser(currentUser);
		
		List<PhotoInfo> photos = request.getPhotos();
		
		File root = new File(photoStore.getBaseDir());
		File userFolder = new File(root, currentUser.getId());
		if(!userFolder.exists()){
			userFolder.mkdir();
		}
		
		File eventFolder = new File(userFolder, eventId);
		if(!eventFolder.exists()){
			eventFolder.mkdir();
		}
		
		
		for(PhotoInfo p:photos){
			byte[] decodedBytes = Base64.decodeBase64(p.getPhoto());
			
			String format = PhotoStore.parseFormat(decodedBytes);
			if(format.equals(PhotoStore.BAD_FORMAT)){
				//TODO
			}else{
				String id = UUID.randomUUID().toString();
				File f = new File(eventFolder, id+"."+format);
				try(FileOutputStream fos = new FileOutputStream(f)){
			
					fos.write(decodedBytes);
					
					Photo aPhoto = new Photo();
					aPhoto.setId(id);
					aPhoto.setFormat(format);
					aPhoto.setMoment(model);
					
					model.getPhotos().add(aPhoto);
					
				}catch(Exception ex){
					log.error(ex);
				}
			}
		}
		
		model = momentRepository.save(model);
		
		MomentRepsone resp = new MomentRepsone();
		resp.setId(model.getId());
		resp.setMoment(model.getMoment());
		resp.setVisibility(model.getVisibility());
		
		User sender = new User();
		sender.setId(model.getUser().getId());
		sender.setNickName(model.getUser().getNickName());
		resp.setSender(sender);
		
		resp.setPublishTime(model.getUpdateTime());
		
		
		if(model.getPhotos()!=null&&model.getPhotos().size()>0){
			resp.setPhotos(new LinkedList<PhotoInfo>());
		}
		
		for(Photo p :model.getPhotos()){
			PhotoInfo info = new PhotoInfo();
			info.setId(p.getId());
			model.getEvent().getId();
			p.getId();
			p.getFormat();
			info.setPhoto("photoStore/"+model.getUser().getId()+"/"+model.getEvent().getId()+"/"+p.getId()+"."+p.getFormat());
			
			resp.getPhotos().add(info);
		}
		
		
		return ResponseUtil.buildResponse(resp);
	}
}
