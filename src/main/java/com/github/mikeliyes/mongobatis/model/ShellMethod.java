package com.github.mikeliyes.mongobatis.model;

import java.util.HashMap;
import java.util.Map;

public class ShellMethod {
	
	public static String placeHolderPre ="#{";
	
	public static String placeHolderSuf = "}";
	
	private String id;
	
	private String nameSpace;
	
	/** fullMethodName = namespace + . + id **/
    private String fullMethodName;
   
    private String methodType;
	
	private String shell;
	
	private Map<String,Param> parameters;

	public String getFullMethodName() {
		return fullMethodName;
	}
	
	public void setFullMethodName(String fullMethodName) {
		this.fullMethodName = fullMethodName;
	}
	
	public String getMethodType() {
		return methodType;
	}
	
	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShell() {
		return shell;
	}

	public void setShell(String shell) {
		this.shell = shell;
	}

	public Map<String, Param> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Param> parameters) {
		this.parameters = parameters;
	}
	
	public void setParameters(Param param) {
		if (this.parameters == null){
			this.parameters = new HashMap<String,Param>();
		}
		this.parameters.put(param.getName(), param);
	}

	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}
	
}
