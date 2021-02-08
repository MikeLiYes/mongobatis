package com.github.mikeliyes.mongobatis.model;

import java.util.Map;

public class Aggregate {
	
	private String nameSpace;

	private String id;
	
	private StringBuilder shell;
	
	private Map<String,Object> parameters;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public StringBuilder getShell() {
		return shell;
	}

	public void setShell(StringBuilder shell) {
		this.shell = shell;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

}
