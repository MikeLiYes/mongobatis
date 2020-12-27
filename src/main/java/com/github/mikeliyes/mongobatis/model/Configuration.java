package com.github.mikeliyes.mongobatis.model;

import java.util.List;

/**
 * mongodb-configuration.xml
 */
public class Configuration {
	
	private DataSource dataSource;

	private List<Mapper> mappers;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Mapper> getMappers() {
		return mappers;
	}

	public void setMappers(List<Mapper> mappers) {
		this.mappers = mappers;
	}
	
}
