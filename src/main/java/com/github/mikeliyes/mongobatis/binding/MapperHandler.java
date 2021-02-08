package com.github.mikeliyes.mongobatis.binding;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperHandler implements InvocationHandler {

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
	    return method.invoke(this, args);
	}

}
