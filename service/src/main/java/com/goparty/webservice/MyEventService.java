package com.goparty.webservice;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.apache.cxf.annotations.GZIP;
import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.Descriptions;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;

import com.goparty.data.model.Event;

@Path("/myevents/")
@WebService
@GZIP
public interface MyEventService {
	@WebMethod
	@GET
	@Descriptions({
		@Description(value = "return my events", target = DocTarget.METHOD),
		@Description(value = "return my events", target = DocTarget.RETURN)
	})
	public Response list(@HeaderParam("token") String token, @QueryParam("after") Date after, @QueryParam("before") Date before, @QueryParam("categories") String categories, @QueryParam("search") String search, @QueryParam("offset") long offset, @QueryParam("limit") long limit);
}
