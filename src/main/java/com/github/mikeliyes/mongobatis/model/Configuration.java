package com.github.mikeliyes.mongobatis.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.mikeliyes.mongobatis.executor.ShellExecutor;

/**
 * mongodb-configuration.xml
 */
public class Configuration {
	
	private Map<String,DataSource> dataSources;

	private List<Mapper> mappers;
	
//	protected Mapper mapperRegistry = new Mapper(this);

	public List<Mapper> getMappers() {
		return mappers;
	}
	
	public Map<String,DataSource> getDataSources() {
		return dataSources;
	}

	public void setDataSources(Map<String,DataSource> dataSources) {
		this.dataSources = dataSources;
	}

	public void addDataSource(DataSource dataSource) {
		
		if (this.dataSources == null || this.dataSources.size() == 0) {
			this.dataSources = new HashMap<String,DataSource>();
		}
		
		if (dataSource != null && dataSource.getEnvId() != null && dataSource.getEnvId().trim() != "") {
			this.dataSources.put(dataSource.getEnvId(),dataSource);
		}
		
	}

	public void setMappers(List<Mapper> mappers) {
		this.mappers = mappers;
	}
	 
	public ShellExecutor newExecutor(){
		return new ShellExecutor(this);
	} 
	
	public void getMapper() {
		
	}
	
//	public <T> T getMapper(Class<T> type, Session session) {
//		return new Mapper();
//	    return mapperRegistry.getMapper(type, session);
//	}
}
