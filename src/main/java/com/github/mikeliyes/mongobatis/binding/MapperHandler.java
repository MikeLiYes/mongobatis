package com.github.mikeliyes.mongobatis.binding;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import com.github.mikeliyes.mongobatis.exception.MessageException;
import com.github.mikeliyes.mongobatis.model.Configuration;
import com.github.mikeliyes.mongobatis.model.ShellMethod;

public class MapperHandler implements InvocationHandler {
	
	private Configuration configuration;
	
    public MapperHandler(Configuration configuration) {
    	this.configuration = configuration;
	}	

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		handleConfiguration(method,args);
		return null;
	}

	private void handleConfiguration(Method method,Object[] args) {
		if (this.configuration == null) {
			return;
		}
		
		Parameter[] parameters=method.getParameters();
		if (parameters == null || parameters.length != 1) {
			throw new MessageException("method args are not allowed more than two");
		}
		
		Class clazz = method.getDeclaringClass();
		String name = clazz.getName();
		String methodName = method.getName();
		String fullMethodName = name+"."+methodName;
		ShellMethod shellMethod = this.configuration.getShellMethod(fullMethodName);

		for(int j=0;j<parameters.length;j++)
		{
			String paramName = parameters[j].getName();
			Class paramType = parameters[j].getType();
			
			
			
			System.out.println(parameters[j].getName()+"~~~~"+parameters[j].getType());
		}

		
		System.out.println("");
		
	}

}
