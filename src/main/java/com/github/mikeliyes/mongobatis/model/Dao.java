package com.github.mikeliyes.mongobatis.model;

import java.util.HashMap;
import java.util.Map;

public class Dao {

	/*********config.xml dao start**************/ 
	private String resource;
	/*********config.xml dao end**************/ 
	
	/**********dao.xml start*****************/
	private String nameSpace;
	
	private Map<String,ShellMethod> shellMethods;

	/**********dao.xml end*****************/

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	public Map<String, ShellMethod> getShellMethods() {
		return shellMethods;
	}

	public void setShellMethods(Map<String, ShellMethod> shellMethods) {
		this.shellMethods = shellMethods;
	}
	
	public void setShellMethod(ShellMethod shellMethod) {
		if (shellMethods == null) {
			this.shellMethods = new HashMap<String,ShellMethod>();
		}
		
		this.shellMethods.put(shellMethod.getFullMethodName(), shellMethod);
	}
	
}
