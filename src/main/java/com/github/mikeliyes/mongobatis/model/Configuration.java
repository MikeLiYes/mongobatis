package com.github.mikeliyes.mongobatis.model;

import java.util.HashMap;
import java.util.Map;

import com.github.mikeliyes.mongobatis.binding.DaoProxy;
import com.github.mikeliyes.mongobatis.utils.StringUtils;

/**
 * mongodb-configuration.xml
 */
public class Configuration {
	
	private Map<String,DataSource> dataSources;
	
	private String dataSourceEnv;

	private Map<String,Dao> daos;

	private Map<String,ShellMethod> shellMethods = new HashMap<String,ShellMethod>();;
	
	private DaoProxy proxy;
	
	public Configuration() {
		if (this.dataSources == null || this.dataSources.size() == 0) {
			this.dataSources = new HashMap<String,DataSource>();
		}
		
		if (this.daos == null || this.daos.size() == 0) {
			this.daos = new HashMap<String,Dao>();
		}
		
		if (this.proxy == null) {
			this.proxy = new DaoProxy(this);
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

	public <T> T getDao(Class<T> type) {
		return (T)proxy.newInstance(type);
	}
	
    public void setDao(Dao dao) {
		if (dao != null && StringUtils.isNotBlank(dao.getResource())) {
			this.daos.put(dao.getResource(),dao);
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

	public String getDataSourceEnv() {
		return dataSourceEnv;
	}

	public void setDataSourceEnv(String dataSourceEnv) {
		this.dataSourceEnv = dataSourceEnv;
	}
    
}
