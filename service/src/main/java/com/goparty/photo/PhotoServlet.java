package com.goparty.photo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

@Component("photoServlet")
public class PhotoServlet implements HttpRequestHandler {
	@Autowired
	private PhotoStore photoStore;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Log log = LogFactory.getLog(PhotoServlet.class);

	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String baseDir = photoStore.getBaseDir();

		String relativePath = trimToEmpty(request.getPathInfo());

		String fullPath = baseDir + File.separator + relativePath;


		log.info("Looking for file " + fullPath);

		// show a 404 page
		if (!Files.exists(Paths.get(fullPath)) || Files.isDirectory(Paths.get(fullPath))) {
			httpError(404, response);
		} else {
			try {
				streamImageFile(fullPath, response);
			} catch (Exception ex) {
				httpError(500, response);
				log.error(ex);
			}
		}

	}

	private void streamImageFile(String fullPath, HttpServletResponse response) {
		// find the right MIME type and set it as content type
		response.setContentType(getContentType(fullPath));
		
		BufferedOutputStream bos = null;
		try {
			
			
			byte[] bytes = Files.readAllBytes(Paths.get(fullPath));
			response.setContentLength(bytes.length);
			
			bos = new BufferedOutputStream(response.getOutputStream());
			bos.write(bytes);
			
		} catch (Exception e) {
			log.error(e);

			throw new RuntimeException(e);
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
		}
	}

	private String getContentType(String file) {
		if (file.length() > 0) {
			String[] parts = file.split("\\.");
			if (parts.length > 0) {
				// only last part interests me
				String extention = parts[parts.length - 1];
				if (extention.equalsIgnoreCase("jpg")) {
					return "image/jpg";
				}else if(extention.equalsIgnoreCase("jpeg")){
					return "image/jpeg";
				}
				
				else if (extention.equalsIgnoreCase("gif")) {
					return "image/gif";
				} else if (extention.equalsIgnoreCase("png")) {
					return "image/png";
				}
			}
		}
		throw new RuntimeException("Can not find content type for the file "+ file);
	}

	private String trimToEmpty(String pathInfo) {
		if (pathInfo == null) {
			return "";
		} else {
			return pathInfo.trim();
		}
	}

	private void httpError(int statusCode, HttpServletResponse response) {
		try {
			response.setStatus(statusCode);
			response.setContentType("text/html");
			PrintWriter writer = response.getWriter();
			writer.append("<html><body><h1>Error Code: " + statusCode
					+ "</h1><body></html>");
			writer.flush();
		} catch (IOException e) {
			log.error(e);
		}
	}
}
