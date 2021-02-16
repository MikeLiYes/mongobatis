package com.github.mikeliyes.mongobatis.model;

import java.util.ArrayList;
import java.util.List;

public class ShellMethod {
	
	public static String PLACE_HOLDER_PRE ="#{";
	
	public static String PLACE_HOLDER_SUF = "}";
	
	public static String SPLITE_START = "([{";
	
	public static String SPLITE_END = "}])";
	
    public static String PIPE_START = "{";
	
	public static String PIPE_END = "}";
	
	public static String PIPE_SPLITE = "},\\{";

	public static String METHOD_TYPE_AGGREGATE ="aggregate"; 
	
	public static String COLLECTION_NAME_START = "db.";
	
	public static String COLLECTION_NAME_END = ".aggregate";
	
	private String id;
	
	private String nameSpace;
	
	/** fullMethodName = namespace + . + id **/
    private String fullMethodName;
   
    private String methodType;
	
	private String shell;
	
	private String collectionName;
	
	private List<String> splitShell;
	
	private List<Integer> splitePlaceLoc;

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

	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	public List<String> getSplitShell() {
		return splitShell;
	}

	public void setSplitShell(List<String> splitShell) {
		this.splitShell = splitShell;
	}

	public List<Integer> getSplitePlaceLoc() {
		return splitePlaceLoc;
	}

	public void setSplitePlaceLoc(Integer loc) {
		if (this.splitePlaceLoc == null) {
			this.splitePlaceLoc = new ArrayList<Integer>();
		}
		
		this.splitePlaceLoc.add(loc);
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
	
}
