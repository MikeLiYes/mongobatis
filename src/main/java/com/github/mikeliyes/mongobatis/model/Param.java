package com.github.mikeliyes.mongobatis.model;

public class Param {
	
	private Class paramType;
	
	private String name;
	
	private Object value;

	public Class getParamType() {
		return paramType;
	}

	public void setParamType(Class paramType) {
		this.paramType = paramType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
