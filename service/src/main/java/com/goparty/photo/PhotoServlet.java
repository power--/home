package com.goparty.photo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
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

		File file = new File(fullPath);

		log.info("Looking for file " + file.getAbsolutePath());

		// show a 404 page
		if (!file.exists() || !file.isFile()) {
			httpError(404, response);
		} else {
			try {
				streamImageFile(file, response);
			} catch (Exception ex) {
				httpError(500, response);
				log.error(ex);
			}
		}

	}

	private void streamImageFile(File file, HttpServletResponse response) {
		// find the right MIME type and set it as content type
		response.setContentType(getContentType(file));
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			response.setContentLength((int) file.length());

			// Use Buffered Stream for reading/writing.
			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(response.getOutputStream());

			byte[] buff = new byte[(int) file.length()];
			int bytesRead;

			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			log.error(e);

			throw new RuntimeException(e);
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
		}
	}

	private String getContentType(File file) {
		if (file.getName().length() > 0) {
			String[] parts = file.getName().split("\\.");
			if (parts.length > 0) {
				// only last part interests me
				String extention = parts[parts.length - 1];
				if (extention.equalsIgnoreCase("jpg")) {
					return "image/jpg";
				} else if (extention.equalsIgnoreCase("gif")) {
					return "image/gif";
				} else if (extention.equalsIgnoreCase("png")) {
					return "image/png";
				}
			}
		}
		throw new RuntimeException("Can not find content type for the file "
				+ file.getAbsolutePath());
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
