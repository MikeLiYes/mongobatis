package com.github.mikeliyes.mongobatis.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.github.mikeliyes.mongobatis.model.Constants;

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
	
	/**
     * convert object to map
     * @param obj
     * @return map
     */
    public static Map<String, Object> objectToMap(Object object) {
    	if (object == null) {
    		return null;
    	}
        Map<String, Object> map = new HashMap<String,Object>();
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = null;
			try {
				value = field.get(object);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} 
            map.put(fieldName, value);
        }
        return map;
    }
    
    /**
     * convert object to map
     * @param obj
     * @return map
     */
    public static Map<String, Object> objectToExpressMap(String collectionName,Object object) {
    	if (object == null) {
    		return null;
    	}
        Map<String, Object> map = new HashMap<String,Object>();
        Class<?> clazz = object.getClass();
        String pre = Constants.getPlaceHolderOjbectPre(collectionName);
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = null;
			try {
				value = field.get(object);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} 
            map.put(pre + fieldName + Constants.PLACE_HOLDER_SUF, value);
        }
        return map;
    }
	
}
