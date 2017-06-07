package com.youedata.http.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;


public class Prop {
	
private Properties properties = null;
	
	public Prop(String fileName) {
		this(fileName, "utf-8");
	}
	
	public Prop(String fileName, String encoding) {
		InputStream inputStream = null;
		try {
			inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);		// properties.load(Prop.class.getResourceAsStream(fileName));
			if (inputStream == null)
				throw new IllegalArgumentException("Properties file not found in classpath: " + fileName);
			properties = new Properties();
			properties.load(new InputStreamReader(inputStream, encoding));
		} catch (IOException e) {
			throw new RuntimeException("Error loading properties file.", e);
		}
		finally {
			if (inputStream != null) try {inputStream.close();} catch (IOException e) {e.printStackTrace();}
		}
	}
	
	public Prop(File file) {
		this(file, "utf-8");
	}
	
	public Prop(File file, String encoding) {
		if (file == null)
			throw new IllegalArgumentException("File can not be null.");
		if (file.isFile() == false)
			throw new IllegalArgumentException("File not found : " + file.getName());
		
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			properties = new Properties();
			properties.load(new InputStreamReader(inputStream, encoding));
		} catch (IOException e) {
			throw new RuntimeException("Error loading properties file.", e);
		}
		finally {
			if (inputStream != null) try {inputStream.close();} catch (IOException e) {e.printStackTrace();}
		}
	}
	
	public String get(String key) {
		return properties.getProperty(key);
	}
	
	public String get(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}
	
	public Integer getInt(String key) {
		return getInt(key, null);
	}
	
	public Integer getInt(String key, Integer defaultValue) {
		String value = properties.getProperty(key);
		return (value != null) ? Integer.parseInt(value) : defaultValue;
	}
	
	public Long getLong(String key) {
		return getLong(key, null);
	}
	
	public Long getLong(String key, Long defaultValue) {
		String value = properties.getProperty(key);
		return (value != null) ? Long.parseLong(value) : defaultValue;
	}
	
	public Boolean getBoolean(String key) {
		return getBoolean(key, null);
	}
	
	public Boolean getBoolean(String key, Boolean defaultValue) {
		String value = properties.getProperty(key);
		return (value != null) ? Boolean.parseBoolean(value) : defaultValue;
	}
	
	public boolean containsKey(String key) {
		return properties.containsKey(key);
	}
	
	public Properties getProperties() {
		return properties;
	}
}
