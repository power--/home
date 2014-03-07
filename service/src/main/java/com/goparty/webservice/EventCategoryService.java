package com.goparty.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.apache.cxf.annotations.GZIP;
import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.Descriptions;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;

import com.goparty.data.model.EventCategory;

@Path("/eventcategories/")
@WebService
@GZIP
public interface EventCategoryService {
	
	@WebMethod
	@GET
	@Descriptions({
		@Description(value = "event categories", target = DocTarget.METHOD),
		@Description(value = "event categories", target = DocTarget.RETURN)
	})
	public List<EventCategory> list();
}
