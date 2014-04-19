package com.goparty.data.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.goparty.data.model.Moment;



@Repository("MomentDao")
@Transactional
public class MomentDao {
	private static Log log = LogFactory.getLog(MomentDao.class);
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Moment> list(String eventId, String token,int offset, int limit, Date before, Date after, String keyword){
		StringBuffer sql = new StringBuffer();
		sql.append("from Moment m where m.event.id=:eventId");
		
		if(before!=null){
			sql.append("and m.updateTime <= :before");
		}
		
		if(after !=null){
			sql.append("and m.updateTime >= :after");
		}
		
		if(keyword !=null&&(!"".equals(keyword.trim()))){
			sql.append("and m.moment like  CONCAT('%',CONCAT(:moment, '%'))");
		}
		
		Query query = em.createQuery(sql.toString());
		
		query.setParameter("eventId", eventId);
		
		if(before!=null){
			query.setParameter("before", before);
		}
		
		if(after !=null){
			query.setParameter("after", after);
		}
		
		if(keyword !=null&&(!"".equals(keyword.trim()))){
			query.setParameter("after", keyword);
		}
		
		query.setFirstResult(offset);
		
		if(limit==0){
			limit = 1;
		}
		
		query.setMaxResults(limit);

		List<Moment> ret = query.getResultList();
		return ret;
	}

}
