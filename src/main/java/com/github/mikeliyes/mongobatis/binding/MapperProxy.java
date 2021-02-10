package com.github.mikeliyes.mongobatis.binding;

import java.lang.reflect.Proxy;

import com.github.mikeliyes.mongobatis.model.Configuration;

public class MapperProxy {
	
	private MapperHandler mapperHandler;

	public MapperProxy(Configuration configuration) {
		if (this.mapperHandler == null) {
			this.mapperHandler = new MapperHandler(configuration);
		}
	}

	public <T> T newInstance(Class<T> mapperInterface) {
	    return (T)Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] { mapperInterface }, this.mapperHandler);
	}
	
}
