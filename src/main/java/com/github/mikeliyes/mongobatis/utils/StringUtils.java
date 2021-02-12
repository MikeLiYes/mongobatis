package com.github.mikeliyes.mongobatis.utils;

public class StringUtils {

	public static boolean isBlank(String str){
		if (str == null || str.trim().equalsIgnoreCase("")) {
			return true;
		}
		return false;
	}
	
	public static boolean isNotBlank(String str){
		if (str == null || str.trim().equalsIgnoreCase("")) {
			return false;
		}
		return true;
	}
	
	/** 
     * Judge regx whether appear twice
     */  
    public static boolean isSingle(String str, String searchText) {  
    	if (str.indexOf(searchText) > -1) {  
        	
        	int first = str.indexOf(searchText);
        	int second = str.indexOf(str, first);
        	if (second > -1) {
        		return true;
        	}
        	
        }  
        return false;  
    }  
}
