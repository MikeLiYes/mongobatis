package com.github.mikeliyes.mongobatis.model;

import java.util.HashMap;
import java.util.Map;

import com.github.mikeliyes.mongobatis.executor.ShellExecutor;

/**
 * mongodb-configuration.xml
 */
public class Configuration {
	
	private Map<String,DataSource> dataSources;

	private Map<String,Mapper> mappers;
	
	private Map<String,Aggregate> aggregates;
	
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
	}
	
	public Map<String,DataSource> getDataSources() {
		return dataSources;
	}

	public void setDataSource(DataSource dataSource) {
		
		if (dataSource != null && dataSource.getId() != null && dataSource.getId().trim() != "") {
			this.dataSources.put(dataSource.getId(),dataSource);
		}
		
	}	

	public Map<String,Mapper> getMappers() {
		return mappers;
	}
	
    public void setMapper(Mapper mapper) {
		if (mapper != null && mapper.getId() != null && mapper.getId().trim() != "") {
			this.mappers.put(mapper.getId(),mapper);
		}
		
	}	
	
	public Map<String, Aggregate> getAggregates() {
		return aggregates;
	}

	public void setAggregate(Aggregate aggregate) {
		if (aggregate != null && aggregate.getId() != null && aggregate.getId().trim() != "") {
			this.aggregates.put(aggregate.getId(),aggregate);
		}
	}

	public ShellExecutor newExecutor(){
		return new ShellExecutor(this);
	} 
	
	public void getMapper() {
		
	}
}
