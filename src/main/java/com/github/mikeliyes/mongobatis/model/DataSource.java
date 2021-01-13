package com.github.mikeliyes.mongobatis.model;

import java.util.HashMap;
import java.util.Map;

public class DataSource{
   
	private String name;
	
	private String password;
	
	private String url;
	
	private Map<String,String> mappers = new HashMap<String,String>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Object getMapper(String name) {
		return mappers.get(name);
	}

	public void setMapper(String name,String resource) {
		if (name == null || name.trim().equalsIgnoreCase("")) {
			return;
		}
		mappers.put(name, resource);
	}
	
	
	
}
