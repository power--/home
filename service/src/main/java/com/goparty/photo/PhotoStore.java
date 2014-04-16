package com.goparty.photo;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class PhotoStore {
	public static String BAD_FORMAT = "bad format";
	
	private String baseDir = null;

	public String getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}

	

	public static String parseFormat(byte[] decodedBytes) {
		String ret = null;
		try {
			InputStream bais = new ByteArrayInputStream(decodedBytes);
			ImageInputStream iis = ImageIO.createImageInputStream(bais);

			Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
			if (ImageIO.getImageReaders(iis).hasNext()) {
				ImageReader reader = iter.next();
				reader.setInput(iis);
				ret = reader.getFormatName();
			}

		} catch (Exception ex) {
			ret = BAD_FORMAT;
		}

		return ret;

	}
}
