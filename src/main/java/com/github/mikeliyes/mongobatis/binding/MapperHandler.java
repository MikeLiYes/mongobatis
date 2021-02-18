package com.github.mikeliyes.mongobatis.binding;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.BsonDocument;
import org.bson.Document;

import com.github.mikeliyes.mongobatis.exception.MessageException;
import com.github.mikeliyes.mongobatis.model.Configuration;
import com.github.mikeliyes.mongobatis.model.Constants;
import com.github.mikeliyes.mongobatis.model.ShellMethod;
import com.github.mikeliyes.mongobatis.utils.CommonUtils;
import com.github.mikeliyes.mongobatis.utils.MongoUtils;
import com.github.mikeliyes.mongobatis.utils.StringUtils;

public class MapperHandler implements InvocationHandler {
	
	private Configuration configuration;
	
    public MapperHandler(Configuration configuration) {
    	this.configuration = configuration;
	}	

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		ShellMethod shellMethod = judge(proxy,method,args);
        return handle(shellMethod,args);
	}

	private ShellMethod judge(Object proxy,Method method,Object[] args) {

		if (this.configuration == null) {
			throw new MessageException("invoke method : config.xml is null");
		}
		
		Class clazz = method.getDeclaringClass();
		String name = clazz.getName();
		String methodName = method.getName();
		String fullMethodName = name+"."+methodName;
		
		if (args != null && args.length != 1) {
			throw new MessageException("invoke method :  param are not allowed more than two : "+fullMethodName);
		}
		
		ShellMethod shellMethod = this.configuration.getShellMethod(fullMethodName);
		if (shellMethod == null ) {
			throw new MessageException("invoke method : no such method in xml : "+fullMethodName);
		}
		
		String shell = shellMethod.getShell();
		if (StringUtils.isBlank(shell) ) {
			throw new MessageException("invoke method : no such shell for method : "+fullMethodName);
		}
		
		return shellMethod;
	}
	
	private List<Document> handle(ShellMethod shellMethod, Object[] args) {
		
		Object param = args[0];
		
		List<Document> objList =  new ArrayList<Document>();
		
		if (args == null 
				|| args.length == 0 
				|| shellMethod.getSplitePlaceLoc() == null 
				|| shellMethod.getSplitePlaceLoc().size() == 0) {
			//no param
			runAggregate(shellMethod,shellMethod.getSplitShell());
		}else {
			//one param
			objList = replaceOneShell(shellMethod,param);
			
			//one object param
			if (objList == null) {
				objList = replaceObjectShell(shellMethod,param);
			}
		}
		
		return objList;
	}

	/**
	 * replace object param shell
	 * @param shell
	 * @param expression
	 * @param param
	 */
	private List<Document> replaceObjectShell(ShellMethod shellMethod,Object param) {
	
		boolean isBase = CommonUtils.isBaseType(param);
		
		Map<String, Object> paramMap = CommonUtils.objectToMap(param);
		
		List<Integer> locs = shellMethod.getSplitePlaceLoc();
		List<String> spliteShells = shellMethod.getSplitShell();
		
		for (int i = 0; i<locs.size();i++) {
			Integer loc = locs.get(i);
			String spliteShell = spliteShells.get(loc);
			
			
		}
		
		
		if (!isBase && expression.indexOf(".") == -1) {
			throw new MessageException("Xml object param should use #{xxx.xxx} to express,not  : "+expression);
		}
		
		if (StringUtils.isNotBlank(expression) 
				&& expression.indexOf(".") > 0
				&& !CommonUtils.isBaseType(param)) {
			
			
			
		}
		
		return null;
	}

	/** 
	 * replace one param shell
	 * @param shell
	 * @param expression
	 */
	private List<Document> replaceOneShell(ShellMethod shellMethod,Object param) {
		
        String shell = shellMethod.getShell();		
		
        String expression = StringUtils.getSubStringInclude(shell, Constants.PLACE_HOLDER_PRE, Constants.PLACE_HOLDER_SUF);
     
		if (StringUtils.isNotBlank(expression) 
				&& expression.indexOf(".") == -1
				&& CommonUtils.isBaseType(param)) {
			
			String replceParam = String.valueOf(param);
			if (param != null 
					&& param instanceof String) {
				replceParam = "'"+ replceParam +"'";
			}
			
			return replaceAggregateOneShell(shellMethod, expression, replceParam);
		}
			
		return null;

	}

	private List<Document> replaceAggregateOneShell(ShellMethod shellMethod,
			String expression, String replceParam) {
		if (shellMethod != null 
				&& Constants.METHOD_TYPE_AGGREGATE.equalsIgnoreCase(shellMethod.getMethodType())) {
			List<Integer> locs = shellMethod.getSplitePlaceLoc();
			List<String> spliteShells = new ArrayList<String>(shellMethod.getSplitShell());
			
			if (locs != null && locs.size() > 0) {
				for (Integer loc : locs) {
					String spliteShell = spliteShells.get(loc);
					spliteShell = spliteShell.replace(expression, replceParam);
					spliteShells.set(loc, spliteShell);
				}
			}
			
			return runAggregate(shellMethod, spliteShells);

		}
		return null;
	}

	private List<Document> runAggregate(ShellMethod shellMethod,
			List<String> spliteShells) {
		if(Constants.METHOD_TYPE_AGGREGATE.equalsIgnoreCase(shellMethod.getMethodType())) {
			return null;
		}
		List<BsonDocument> pipeline = new ArrayList<BsonDocument>();
		for (String spliteX : spliteShells) {
			
			if (StringUtils.isBlank(spliteX)) {
				throw new MessageException("Shell has empty {}");
			}
			
			BsonDocument doc = BsonDocument.parse(spliteX.trim());
			pipeline.add(doc);
		}
		return MongoUtils.aggregate(shellMethod.getCollectionName(), pipeline);
	}
}
