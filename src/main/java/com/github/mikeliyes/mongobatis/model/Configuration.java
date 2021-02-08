package com.github.mikeliyes.mongobatis.model;

import java.util.HashMap;
import java.util.Map;

import com.github.mikeliyes.mongobatis.binding.MapperProxy;
import com.github.mikeliyes.mongobatis.executor.ShellExecutor;

/**
 * mongodb-configuration.xml
 */
public class Configuration {
	
	private static String METHOD_TYPE_AGGREGATE ="aggregate"; 
	
	private Map<String,DataSource> dataSources;

	private Map<String,Mapper> mappers;
	
	private Map<String,String> methodTypes;
	
	private Map<String,Aggregate> aggregates;
	
	private MapperProxy factory;
	
	public Configuration() {
		if (this.dataSources == null || this.dataSources.size() == 0) {
			this.dataSources = new HashMap<String,DataSource>();
		}
		
		if (this.mappers == null || this.mappers.size() == 0) {
			this.mappers = new HashMap<String,Mapper>();
		}
		
		if (this.aggregates == null || this.aggregates.size() == 0) {
			this.aggregates = new HashMap<String,Aggregate>();
		}
		
		if (this.factory == null) {
			this.factory = new MapperProxy(this);
		}
		
		if (this.methodTypes == null) {
			this.methodTypes = new HashMap<String,String>();
		}
	}
	
	public Map<String,DataSource> getDataSources() {
		return dataSources;
	}

	public void setDataSource(DataSource dataSource) {
		
		if (dataSource != null && dataSource.getId() != null && dataSource.getId().trim() != "") {
			this.dataSources.put(dataSource.getId(),dataSource);
		}
		
	}

	public <T> T getMapper(Class<T> type) {
		return (T)factory.newInstance(type);
	}
	
    public void setMapper(Mapper mapper) {
		if (mapper != null && mapper.getResource() != null && mapper.getResource().trim() != "") {
			this.mappers.put(mapper.getResource(),mapper);
		}
		
	}	
	
	public Map<String, Aggregate> getAggregates() {
		return aggregates;
	}

	public void setAggregate(Aggregate aggregate) {
		if (aggregate != null 
				&& aggregate.getId() != null 
				&& aggregate.getId().trim() != ""
				&& aggregate.getNameSpace() != null
				&& aggregate.getNameSpace().trim() != "") {
			String fullMethodName = aggregate.getNameSpace()+"."+aggregate.getId();
			this.aggregates.put(fullMethodName,aggregate);
			
			this.methodTypes.put(fullMethodName, METHOD_TYPE_AGGREGATE);
		}
	}
	
	public String getMethodType(String fullMethodName){
		  return this.methodTypes.get(fullMethodName);
	}

	public Map<String, String> getMethodType() {
		return methodTypes;
	}

	public void setMethodType(Map<String, String> methodTypes) {
		this.methodTypes = methodTypes;
	}

	public ShellExecutor newExecutor(){
		return new ShellExecutor(this);
	} 
}
