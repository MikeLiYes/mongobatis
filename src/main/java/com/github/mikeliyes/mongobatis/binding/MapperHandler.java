package com.github.mikeliyes.mongobatis.binding;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.github.mikeliyes.mongobatis.model.Configuration;

public class MapperHandler implements InvocationHandler {
	
	private Configuration configuration;
	
    public MapperHandler(Configuration configuration) {
    	this.configuration = configuration;
	}	

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
//	    return method.invoke(this, args);
		
		handleConfiguration(method,args);
		
		return null;
	}

	private void handleConfiguration(Method method,Object[] args) {
		if (this.configuration == null) {
			return;
		}
		
		String fullMethodName = method.getName();
		
		this.configuration.getMethodType();
	}

}
