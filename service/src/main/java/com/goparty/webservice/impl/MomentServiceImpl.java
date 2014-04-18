package com.goparty.webservice.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.goparty.data.dao.UserDao;
import com.goparty.data.exception.BaseException;
import com.goparty.data.model.Moment;
import com.goparty.data.model.Photo;
import com.goparty.data.model.User;
import com.goparty.data.repository.IMomentRepository;
import com.goparty.webservice.MomentService;
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
	public Response list(String eventId, String token, int offset, int limit) {
		PageRequest pageable = new PageRequest(offset, limit);
		
		List<Moment> list = momentRepository.findByEventId(eventId, pageable);
		
		List<MomentRepsone> respList = new ArrayList<MomentRepsone>(list.size());
		
		for(Moment m :list){
			respList.add(this.buildRespone(m));	
		}
		
		return ResponseUtil.buildResponse(respList);
	}
	
	
}
