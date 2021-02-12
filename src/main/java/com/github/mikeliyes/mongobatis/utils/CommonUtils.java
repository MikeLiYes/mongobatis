package com.github.mikeliyes.mongobatis.utils;

public class CommonUtils {

	/**
	 * Judge object wether base type
	 * @param object
	 * @return
	 */
	public static boolean isBaseDefaultValue(Object object) {
	    Class className = object.getClass();
	    if(className.equals(java.lang.Integer.class)) {
	        return (Integer)object == 0;
	    } else if(className.equals(java.lang.Byte.class)) {
	        return (Byte)object == 0;
	    } else if(className.equals(java.lang.Long.class)) {
	        return (Long)object == 0L;
	    } else if(className.equals(java.lang.Double.class)) {
	        return (Double)object == 0.0d;
	    } else if(className.equals(java.lang.Float.class)) {
	        return (Float)object == 0.0f;
	    } else if(className.equals(java.lang.Character.class)) {
	        return (Character)object == '\u0000';
	    } else if(className.equals(java.lang.Short.class)) {
	        return (Short)object == 0;
	    } else if(className.equals(java.lang.Boolean.class)) {
	        return (Boolean)object == false;
	    }
	    return false;
	}
}
