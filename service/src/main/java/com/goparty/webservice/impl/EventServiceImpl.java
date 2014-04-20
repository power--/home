package com.goparty.webservice.impl;



import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.util.CollectionUtils;

import com.goparty.data.constant.InvitationAcceptance;
import com.goparty.data.constant.InvitationStatus;
import com.goparty.data.constant.MessageType;
import com.goparty.data.dao.CommentDao;
import com.goparty.data.dao.EventDao;
import com.goparty.data.dao.MessageDao;
import com.goparty.data.dao.MomentDao;
import com.goparty.data.dao.UserDao;
import com.goparty.data.exception.BaseException;
import com.goparty.data.model.Event;
import com.goparty.data.model.EventApplication;
import com.goparty.data.model.EventComment;
import com.goparty.data.model.EventInvitation;
import com.goparty.data.model.EventMessage;
import com.goparty.data.model.FriendInvitation;
import com.goparty.data.model.Group;
import com.goparty.data.model.Moment;
import com.goparty.data.model.Photo;
import com.goparty.data.model.User;
import com.goparty.data.model.UserFriend;
import com.goparty.data.repository.IMomentRepository;
import com.goparty.data.vo.FriendInvitatinVo;
import com.goparty.photo.PhotoStore;
import com.goparty.webservice.EventService;
import com.goparty.webservice.model.CommentRequest;
import com.goparty.webservice.model.EventApplicationRequest;
import com.goparty.webservice.model.EventInvitationRequest;
import com.goparty.webservice.model.InvitationResponse;
import com.goparty.webservice.model.MessageRequest;
import com.goparty.webservice.model.MomentRepsone;
import com.goparty.webservice.model.MomentRequest;
import com.goparty.webservice.model.PhotoInfo;
import com.goparty.webservice.model.UserResponse;
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
	
	@Autowired
	private MomentDao momentDao;
	
	
	@Override
	public Response read(String id) {
		Event ret = eventDao.read(id); 
		return ResponseUtil.buildResponse(ret);
	}

	@Override
	public Response create(Event event){
		if(event.getTitle()==null){
			throw new BaseException("The event title should not be null");
		} 
		eventDao.create(event);
		 
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
		if(evt.getMembers().contains(user)){
			throw new BaseException("user_existed_in_attendees"); 			 
		}		
		evt.getMembers().add(user);
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
		if(!evt.getMembers().contains(user)){
			throw new BaseException("user_existed_in_attendees"); 	
		}		
		evt.getMembers().remove(user);
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
	public Response list(String eventId, String token,int offset, int limit,Date before, Date after, String keyword) {
		List<Moment> list = momentDao.list(eventId, token, offset, limit, before, after, keyword);
		
		List<MomentRepsone> resp = new ArrayList<MomentRepsone>(list.size());
		
		for(Moment m : list){
			resp.add(buildMomentRespone(m));
		}
		
		return ResponseUtil.buildResponse(resp);
	}

	@Override
	public Response getUnrespondedInvitations(String token, long offset,
			long limit) {
		User user = userDao.getUserByToken(token);		
		List<EventInvitation> eventInvitatins = eventDao.getUnrespondedInvitations(user.getId(), offset, limit);
		return ResponseUtil.buildResponse(eventInvitatins);
	}

	@Override
	public Response respondedInvitations(String token, String invitationId,
			EventInvitationRequest request) {
		User user = userDao.getUserByToken(token);	
		//invitation
		EventInvitation invitation = eventDao.getInvitation(invitationId);
		if(!user.getId().equals(invitation.getInviteeId())){
			throw new BaseException("You have no permission to respond this invitation.");
		}
		
		if(request.getParticipance().equals(InvitationAcceptance.Y.toString())){
			invitation.setParticipance(InvitationAcceptance.Y);
		}else{
			invitation.setParticipance(InvitationAcceptance.N);
		}
		invitation.setStatus(InvitationStatus.RESP);
		invitation.setInviteeMessage(request.getInviteeMessage());
		invitation.setUpdateTime(new Date());
		eventDao.updateInvitation(invitation);
		
		//update event members
		if(invitation.getParticipance().equals(InvitationAcceptance.Y)){
			
		}		
		
		return ResponseUtil.buildResponse(true);
	}

	@Override
	public Response getRespondedInvitations(String token, long offset,
			long limit) {
		User user = userDao.getUserByToken(token);				
		List<EventInvitation> eventInvitatins = eventDao.getRespondedInvitations(user.getId(), offset, limit);
		return ResponseUtil.buildResponse(eventInvitatins); 
	}

	@Override
	public Response getUnrespondedApplications(String token, long offset,
			long limit) { 
		User user = userDao.getUserByToken(token);		
		List<EventApplication> applications = eventDao.getUnrespondedApplications(user.getId(), offset, limit);
		return ResponseUtil.buildResponse(applications);
	}

	@Override
	public Response respondedApplications(String token, String applicationId,
			EventApplicationRequest request) {
		User user = userDao.getUserByToken(token);	
		
		EventApplication application = eventDao.getApplication(applicationId);
		if(!user.getId().equals(application.getApproverId())){
			throw new BaseException("You have no permission to respond this application.");
		}
		
		if(request.getApproval().equals(InvitationAcceptance.Y.toString())){
			application.setApproval(InvitationAcceptance.Y);
		}else{
			application.setApproval(InvitationAcceptance.N);
		}
		application.setStatus(InvitationStatus.RESP);
		application.setApplicantMessage(request.getApproverMessage());
		application.setUpdateTime(new Date());
		eventDao.updateApplication(application);
		
		//update event members
		if(application.getApproval().equals(InvitationAcceptance.Y)){
			
		}		
		
		return ResponseUtil.buildResponse(true);
	}

	@Override
	public Response getRespondedApplications(String token, long offset,
			long limit) {
		User user = userDao.getUserByToken(token);		
		List<EventApplication> applications = eventDao.getRespondedApplications(user.getId(), offset, limit);
		return ResponseUtil.buildResponse(applications);
	}

	
}
