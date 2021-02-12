package com.github.mikeliyes.mongobatis.binding;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import com.github.mikeliyes.mongobatis.exception.MessageException;
import com.github.mikeliyes.mongobatis.model.Configuration;
import com.github.mikeliyes.mongobatis.model.ShellMethod;
import com.github.mikeliyes.mongobatis.utils.StringUtils;

public class MapperHandler implements InvocationHandler {
	
	private Configuration configuration;
	
    public MapperHandler(Configuration configuration) {
    	this.configuration = configuration;
	}	

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		handleConfiguration(proxy,method,args);
		return null;
	}

	private void handleConfiguration(Object proxy,Method method,Object[] args) {

		if (this.configuration == null) {
			return;
		}
		
		Class clazz = method.getDeclaringClass();
		String name = clazz.getName();
		String methodName = method.getName();
		String fullMethodName = name+"."+methodName;
		
		
		if (args == null || args.length != 1) {
			throw new MessageException("method param are not allowed more than two : "+fullMethodName);
		}
		
		ShellMethod shellMethod = this.configuration.getShellMethod(fullMethodName);
		if (shellMethod == null ) {
			throw new MessageException("no such method in xml : "+fullMethodName);
		}
		
		String shell = shellMethod.getShell();
		if (StringUtils.isBlank(shell) ) {
			throw new MessageException("no such shell for method : "+fullMethodName);
		}
		
		Object param = args[0];
		if (param == null) {
			throw new MessageException("no such param for method : "+fullMethodName);
		}
		
		int first = shell.indexOf(ShellMethod.placeHolderPre);
        
		if (first > 0) {
			int last = shell.indexOf(ShellMethod.placeHolderSuf,first);
			
			String expression = shell.substring(first, last + ShellMethod.placeHolderSuf.length());
			
			// one param
			shell = replaceOneShell(shell,expression,param);
			
			// one param
			shell = replaceObjectShell(shell,expression,param);
		}
		
		
		
			System.out.println("");

		
		System.out.println("");
		
	}

	/**
	 * replace object param shell
	 * @param shell
	 * @param expression
	 * @param param
	 */
	private String replaceObjectShell(String shell, String expression,
			Object param) {
		if (StringUtils.isNotBlank(expression) 
				&& expression.indexOf(".") > 0) {
            
			
			
		}
		return shell;
	}

	/** 
	 * replace one param shell
	 * @param shell
	 * @param expression
	 */
	private String replaceOneShell(String shell, String expression,Object param) {
		if (StringUtils.isNotBlank(expression) 
				&& expression.indexOf(".") == -1) {
            
			String exp = String.valueOf(param);
			if (param != null 
					&& param instanceof String) {
				exp = "'"+ exp +"'";
			}
			
			shell = shell.replace(expression, exp);
			
		}
		return shell;
	}

}
