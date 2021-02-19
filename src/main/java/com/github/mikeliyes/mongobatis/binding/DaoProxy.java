package com.github.mikeliyes.mongobatis.binding;

import java.lang.reflect.Proxy;

import com.github.mikeliyes.mongobatis.model.Configuration;

public class DaoProxy {
	
	private DaoHandler daoHandler;

	public DaoProxy(Configuration configuration) {
		if (this.daoHandler == null) {
			this.daoHandler = new DaoHandler(configuration);
		}
	}

	public <T> T newInstance(Class<T> mapperInterface) {
	    return (T)Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] { mapperInterface }, this.daoHandler);
	}
	
}
