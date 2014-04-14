package com.goparty.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.cxf.annotations.GZIP;
import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.Descriptions;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;

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
	public Response list();
}
