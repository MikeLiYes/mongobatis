package com.github.mikeliyes.mongobatis.binding;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

import com.github.mikeliyes.mongobatis.exception.MessageException;
import com.github.mikeliyes.mongobatis.model.Configuration;
import com.github.mikeliyes.mongobatis.model.ShellMethod;
import com.github.mikeliyes.mongobatis.utils.CommonUtils;
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
		
		int first = shell.indexOf(ShellMethod.PLACE_HOLDER_PRE);
        
		if (first > 0) {
			int last = shell.indexOf(ShellMethod.PLACE_HOLDER_SUF,first);
			
			String expression = shell.substring(first, last + ShellMethod.PLACE_HOLDER_SUF.length());
			
			// one param
			replaceOneShell(shellMethod,expression,param);
			
			// object param
//			replaceObjectShell(shell,expression,param);
			
			
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
	private void replaceObjectShell(String shell, String expression,
			Object param) {
		
		boolean isBase = CommonUtils.isBaseType(param);
		if (!isBase && expression.indexOf(".") == -1) {
			throw new MessageException("Xml object param should use #{xxx.xxx} to express,not  : "+expression);
		}
		
		if (StringUtils.isNotBlank(expression) 
				&& expression.indexOf(".") > 0
				&& !CommonUtils.isBaseType(param)) {
			
		}
	}

	/** 
	 * replace one param shell
	 * @param shell
	 * @param expression
	 */
	private void replaceOneShell(ShellMethod shellMethod, String expression,Object param) {
		if (StringUtils.isNotBlank(expression) 
				&& expression.indexOf(".") == -1
				&& CommonUtils.isBaseType(param)) {
			
			String replceParam = String.valueOf(param);
			if (param != null 
					&& param instanceof String) {
				replceParam = "'"+ replceParam +"'";
			}
			
			replaceAggregateOneShell(shellMethod, expression, replceParam);
			
		}
	}

	private void replaceAggregateOneShell(ShellMethod shellMethod,
			String expression, String replceParam) {
		if (shellMethod != null && ShellMethod.METHOD_TYPE_AGGREGATE.equalsIgnoreCase(shellMethod.getMethodType())) {
			List<Integer> locs = shellMethod.getSplitePlaceLoc();
			for (Integer loc : locs) {
				List<String> spliteShells = shellMethod.getSplitShell();
				String spliteShell = spliteShells.get(loc);
				spliteShell = spliteShell.replace(expression, replceParam);
				spliteShells.set(loc, spliteShell);
			}
			
			
		}
	}
	
	

}
