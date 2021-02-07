package com.github.mikeliyes.mongobatis.model;

public class DataSource {
	
	public String ENV_DEV ="dev";
	public String ENV_TEST ="test";
	public String ENV_PRO ="pro";
	
	private String id;
   
	private String name;
	
	private String password;
	
	private String url;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
