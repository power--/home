package com.goparty.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.apache.cxf.annotations.GZIP;
import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.Descriptions;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;


@Path("/indexdata/")
@WebService
@GZIP
public interface FullTextIndexService {
	@WebMethod
	@GET
	@Descriptions({
		@Description(value = "returns a event by id", target = DocTarget.METHOD),
		@Description(value = "event of the id", target = DocTarget.RETURN)
	})
	public boolean read();
}
