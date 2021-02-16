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
    
    /**
     * zsmnjkt
     * start: s
     * end: k
     * return smnjk
     * 
     * @param fullString
     * @param startString
     * @param endString
     * @return
     */
    public static String getSubStringInclude(String fullString,String startString,String endString){
    	if (StringUtils.isBlank(startString) 
    			|| StringUtils.isBlank(endString)
    			|| StringUtils.isBlank(fullString)) {
    		return null;
    	}
    	
    	int start = fullString.indexOf(startString);
    	
    	if (start >= 0) {
    		int end = fullString.indexOf(endString,start + startString.length());
    		if (end >= 0) {
    			String subString = fullString.substring(start, end + endString.length());
    			return subString;
    		}
    	}
    	
    	return null;
    }
    
    /**
     * zsmnjkt
     * start: s
     * end: k
     * return mnj
     * 
     * @param fullString
     * @param startString
     * @param endString
     * @return
     */
    public static String getSubStringExclude(String fullString,String startString,String endString){
    	if (StringUtils.isBlank(startString) 
    			|| StringUtils.isBlank(endString)
    			|| StringUtils.isBlank(fullString)) {
    		return null;
    	}
    	
    	int start = fullString.indexOf(startString);
    	
    	if (start >= 0) {
    		int end = fullString.indexOf(endString,start + startString.length());
    		if (end >= 0) {
    			String subString = fullString.substring(start + startString.length(), end);
    			return subString;
    		}
    	}
    	
    	return null;
    }
}
