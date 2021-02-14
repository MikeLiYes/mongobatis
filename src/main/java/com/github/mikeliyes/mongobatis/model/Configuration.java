package com.github.mikeliyes.mongobatis.model;

import java.util.HashMap;
import java.util.Map;

import com.github.mikeliyes.mongobatis.binding.MapperProxy;
import com.github.mikeliyes.mongobatis.utils.StringUtils;

/**
 * mongodb-configuration.xml
 */
public class Configuration {
	
	private Map<String,DataSource> dataSources;

	private Map<String,Mapper> mappers;

	private Map<String,ShellMethod> shellMethods = new HashMap<String,ShellMethod>();;
	
	private MapperProxy proxy;
	
	public Configuration() {
		if (this.dataSources == null || this.dataSources.size() == 0) {
			this.dataSources = new HashMap<String,DataSource>();
		}
		
		if (this.mappers == null || this.mappers.size() == 0) {
			this.mappers = new HashMap<String,Mapper>();
		}
		
		if (this.proxy == null) {
			this.proxy = new MapperProxy(this);
		}
	}
	
	public Map<String,DataSource> getDataSources() {
		return dataSources;
	}

	public void setDataSource(DataSource dataSource) {
		
		if (dataSource != null && StringUtils.isNotBlank(dataSource.getId())) {
			this.dataSources.put(dataSource.getId(),dataSource);
		}
		
	}

	public <T> T getMapper(Class<T> type) {
		return (T)proxy.newInstance(type);
	}
	
    public void setMapper(Mapper mapper) {
		if (mapper != null && StringUtils.isNotBlank(mapper.getResource())) {
			this.mappers.put(mapper.getResource(),mapper);
		}
		
	}

	public Map<String, ShellMethod> getShellMethods() {
		return shellMethods;
	}
	
	public ShellMethod getShellMethod(String fullMethodName) {
		 return this.shellMethods.get(fullMethodName);
	}

	public void setShellMethod(ShellMethod shellMethod) {
		if (shellMethod == null) {
			return;
		}
		this.shellMethods.put(shellMethod.getFullMethodName(), shellMethod);
	}
    
}
