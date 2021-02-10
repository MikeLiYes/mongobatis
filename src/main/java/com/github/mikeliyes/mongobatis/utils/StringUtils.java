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
}
