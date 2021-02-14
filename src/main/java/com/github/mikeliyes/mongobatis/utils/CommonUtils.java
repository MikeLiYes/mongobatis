package com.github.mikeliyes.mongobatis.utils;

public class CommonUtils {

	/**
	 * Judge object wether base type
	 * @param object
	 * @return
	 */
	public static boolean isBaseType(Object object) {
	    Class className = object.getClass();
	    if(className.equals(java.lang.Integer.class)) {
	        return true;
	    } else if(className.equals(java.lang.Byte.class)) {
	        return true;
	    } else if(className.equals(java.lang.Long.class)) {
	        return true;
	    } else if(className.equals(java.lang.Double.class)) {
	        return true;
	    } else if(className.equals(java.lang.Float.class)) {
	        return true;
	    } else if(className.equals(java.lang.Character.class)) {
	        return true;
	    } else if(className.equals(java.lang.Short.class)) {
	        return true;
	    } else if(className.equals(java.lang.Boolean.class)) {
	        return true;
	    }
	    return false;
	}
}
