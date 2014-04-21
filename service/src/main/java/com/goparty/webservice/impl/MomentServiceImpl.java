package com.goparty.webservice.impl;

import java.util.LinkedList;

import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.dao.UserDao;
import com.goparty.data.exception.BaseException;
import com.goparty.data.model.Moment;
import com.goparty.data.model.MomentComment;
import com.goparty.data.model.Photo;
import com.goparty.data.model.User;
import com.goparty.data.repository.IMomentCommentRepository;
import com.goparty.data.repository.IMomentRepository;
import com.goparty.webservice.MomentService;
import com.goparty.webservice.model.MomentComentResponse;
import com.goparty.webservice.model.MomentCommentRequest;
import com.goparty.webservice.model.MomentRepsone;
import com.goparty.webservice.model.PhotoInfo;
import com.goparty.webservice.utils.ResponseUtil;

@Service("momentService")
public class MomentServiceImpl implements MomentService {
	private Log log = LogFactory.getLog(MomentServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	
	@Autowired
	private IMomentRepository momentRepository;
	
	@Autowired
	private IMomentCommentRepository momentCommentRepository;

	@Override
	public Response read(String token, String momentId) {
		Moment model = momentRepository.findOne(momentId);
		
		if(model==null){
			throw new BaseException("Moment doesn't exist for id: "+momentId);
		}
		
		MomentRepsone resp = this.buildRespone(model);
		
		return ResponseUtil.buildResponse(resp);
	}
	
	
	private MomentRepsone buildRespone(Moment model){
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
	@Transactional
	public Response delete(String token, String momentId) {
		if(! momentRepository.exists(momentId)){
			throw new BaseException("moment doesn't exist for id: "+momentId);
		}
		
		boolean ret = false;
		try{
			momentRepository.delete(momentId);
			ret = true;
		}catch(Exception ex){
			log.error(ex);
			throw new BaseException("error happened during delete action.");
		}
		
		return ResponseUtil.buildResponse(ret);
		
	}


	@Override
	@Transactional
	public Response addComment(String token, String momentId,
			MomentCommentRequest request) {
		if(momentId ==null){
			throw new BaseException("momentId cannot be null");
		}
		
		if(! momentRepository.exists(momentId)){
			throw new BaseException("Moment dosen't exit, momentId = "+momentId);
		}
		
		if(request.getComment()==null||"".equals(request.getComment().trim())){
			throw new BaseException("Comment text is empty, please check.");
		}
		
		
		MomentComment comment = new MomentComment();
		
		Moment moment = new Moment();
		moment.setId(momentId);
		comment.setMoment(moment);
		
		User user = userDao.getUserByToken(token);
		comment.setUser(user);
		
		comment.setLikeit(request.isLikeit());
		
	    comment.setId(java.util.UUID.randomUUID().toString());
	    
	    comment.setComment(request.getComment());
		
		comment = momentCommentRepository.save(comment);
		
		MomentComentResponse resp  = new MomentComentResponse();
		resp.setId(comment.getId());
		resp.setComment(comment.getComment());
		resp.setLikeit(comment.isLikeit());
		User sender = new User();
		sender.setId(comment.getUser().getId());
		sender.setNickName(comment.getUser().getNickName());
		sender.setPhoto(comment.getUser().getPhoto());
		
		return ResponseUtil.buildResponse(resp);
	}
}
