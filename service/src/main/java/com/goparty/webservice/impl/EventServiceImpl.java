package com.goparty.webservice.impl;



import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.OK;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.constant.MessageType;
import com.goparty.data.dao.CommentDao;
import com.goparty.data.dao.EventDao;
import com.goparty.data.dao.MessageDao;
import com.goparty.data.dao.UserDao;
import com.goparty.data.exception.BaseException;
import com.goparty.data.model.BaseModel;
import com.goparty.data.model.Event;
import com.goparty.data.model.EventComment;
import com.goparty.data.model.EventMessage;
import com.goparty.data.model.Moment;
import com.goparty.data.model.Photo;
import com.goparty.data.model.User;
import com.goparty.data.repository.IMomentRepository;
import com.goparty.photo.PhotoStore;
import com.goparty.webservice.EventService;
import com.goparty.webservice.model.CommentRequest;
import com.goparty.webservice.model.MessageRequest;
import com.goparty.webservice.model.MomentRepsone;
import com.goparty.webservice.model.MomentRequest;
import com.goparty.webservice.model.PhotoInfo;
import com.goparty.webservice.utils.ResponseUtil;

@Service("eventService")
@Transactional
public class EventServiceImpl implements EventService {
	private Log log = LogFactory.getLog(MomentServiceImpl.class);
	
	@Autowired
	private EventDao eventDao;
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private MessageDao messageDataService;
	
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private PhotoStore photoStore;

	@Autowired
	private IMomentRepository momentRepository;
	
	
	@Override
	public Response read(String id) {
		Event ret = eventDao.read(id);
		ret.setAttendees(null);
		ret.setOwner(null);
		return ResponseUtil.buildResponse(ret);
	}

	@Override
	public Response create(Event event){
		if(event.getTitle()==null){
			throw new BaseException("The event title should not be null");
		}
		
		System.out.println(event.getId());
		System.out.println(event.getId());
		eventDao.create(event);
		
		User user = new User();
		user.setId("18");
		List<User> refusedAttendees = new ArrayList<User>();
		refusedAttendees.add(user);
		event.setRefusedAttendees(refusedAttendees);
		return ResponseUtil.buildResponse(event);
	}

	@Override
	public Response update(Event event) {
		eventDao.update(event);
		return ResponseUtil.buildResponse(event);
	}

	@Override
	public Response delete(String id) {
		boolean ret = false;
		try{
			eventDao.delete(id);
			ret = true;
		}catch(Exception ex){
			throw ex;
		}
		return ResponseUtil.buildResponse(ret);
	}

	@Override
	public Response addInvitee(String eventId, String userId) { 
		Event evt = eventDao.read(eventId);		
		if(evt == null){
			throw new BaseException("no_such_event");
		}
		User user = userDao.read(userId);
		if(user == null){
			throw new BaseException("no_such_user"); 
		}
		if(evt.getAttendees().contains(user)){
			throw new BaseException("user_existed_in_attendees"); 			 
		}		
		evt.getAttendees().add(user);
		eventDao.update(evt);
		 
		return ResponseUtil.buildResponse(true);
	}

	@Override
	public Response delInvitee(String eventId, String userId) { 
		Event evt = eventDao.read(eventId);		
		if(evt == null){
			throw new BaseException("no_such_event");
		}		
		User user = userDao.read(userId);
		if(user == null){
			throw new BaseException("no_such_user"); 
		}		
		if(!evt.getAttendees().contains(user)){
			throw new BaseException("user_existed_in_attendees"); 	
		}		
		evt.getAttendees().remove(user);
		eventDao.update(evt);		 
		
		return ResponseUtil.buildResponse(true);
	}

	@Override
	public Response updateSponser(String eventId, String userId) { 
		Event evt = eventDao.read(eventId);		
		if(evt == null){
			throw new BaseException("no_such_event");
		}		
		User user = userDao.read(userId);
		if(user == null){
			throw new BaseException("no_such_user");
		}		
		if(evt.getOwner().equals(user)){
			throw new BaseException("cannot_use_yourself"); 	
		}		
		evt.setOwner(user);
		eventDao.update(evt);
		
		return ResponseUtil.buildResponse(true);
	}

	@Override
	public Response publishMessage(String token, String eventId, MessageRequest request) {
		User user = userDao.getUserByToken(token);
		EventMessage msg = new EventMessage();
		msg.setUserId(user.getId());
		msg.setEventId(eventId);
		msg.setContent(request.getContent());
		msg.setPublishTime(new Date());
		msg.setType(MessageType.USER_MESSAGE);
		msg = messageDataService.create(msg );
		return ResponseUtil.buildResponse(msg);
	}

	@Override
	public Response getMessageListByEventId(String eventId,int offset,int limit) {		
		return ResponseUtil.buildResponse(messageDataService.findByEventId(eventId, offset, limit));
	}

	@Override
	public Response comment(String token, String eventId,
			CommentRequest request) {
		User user = userDao.getUserByToken(token);
		EventComment comment = new EventComment();
		comment.setContent(request.getContent());
		comment.setEventId(eventId); 
		comment.setUserId(user.getId());
		comment.setPublishTime(new Date());
		return ResponseUtil.buildResponse(commentDao.create(comment));
	}

	@Override
	public Response getCommentListByEventId(String eventId,
			int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Response list(String token, String scope, Date after, Date before,
			String categories, String search, long offset, long limit) {
		User user = userDao.getUserByToken(token);		
		List<Event> ret = eventDao.getEvents(user.getId(),scope,after, before,categories,search,offset,limit);
		return ResponseUtil.buildResponse(ret);
	}
	
	
	
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
		
		MomentRepsone resp = this.buildMomentRespone(model);
		
		return ResponseUtil.buildResponse(resp);
	}
	
	private MomentRepsone buildMomentRespone(Moment model){
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
		
		return resp;
	}
	
	
	@Override
	public Response list(String eventId, String token,int offset, int limit) {
		PageRequest pageable = new PageRequest(offset, limit);
		
		List<Moment> list = momentRepository.findByEventId(eventId, pageable);
		
		List<MomentRepsone> respList = new ArrayList<MomentRepsone>(list.size());
		
		for(Moment m :list){
			respList.add(this.buildMomentRespone(m));	
		}
		
		return ResponseUtil.buildResponse(respList);
	}

	
}
