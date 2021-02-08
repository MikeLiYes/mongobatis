package com.github.mikeliyes.mongobatis.binding;

import java.lang.reflect.Proxy;

public class MapperProxyFactory {
	
	public MapperHandler mapperHandler = new MapperHandler();

	public <T> T newInstance(Class<T> mapperInterface) {
	    return (T)Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] { mapperInterface }, this.mapperHandler);
	}
	
}
