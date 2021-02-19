package com.github.mikeliyes.mongobatis.model;

import com.github.mikeliyes.mongobatis.utils.StringUtils;

public class Constants {
	/********** xml dao constants start *********************/
	public static String DAO = "dao";
	
	public static String NAME_SPACE = "namespace";
	/********** xml dao constants start *********************/
	
    /********** xml method constants start *********************/
    public static String PLACE_HOLDER_PRE ="#{";
	
	public static String PLACE_HOLDER_SUF = "}";
	
	public static String PLACE_HOLDER_PRE_REPLACE ="#\\{";
	
	public static String getPlaceHolderOjbectPreReplace(String collectionName){
		if(StringUtils.isBlank(collectionName)){
			return Constants.PLACE_HOLDER_PRE_REPLACE;
		}
		return Constants.PLACE_HOLDER_PRE_REPLACE+collectionName+".";
	} 
	
	public static String getPlaceHolderOjbectPre(String collectionName){
		if(StringUtils.isBlank(collectionName)){
			return Constants.PLACE_HOLDER_PRE;
		}
		return Constants.PLACE_HOLDER_PRE+collectionName+".";
	} 
	
	public static String SPLITE_START = "([{";
	
	public static String SPLITE_END = "}])";
	
    public static String PIPE_START = "{";
	
	public static String PIPE_END = "}";
	
	public static String PIPE_SPLITE = "},\\{";

	public static String METHOD_TYPE_AGGREGATE ="aggregate"; 
	
	public static String COLLECTION_NAME_START = "db.";
	
	public static String COLLECTION_NAME_END = ".aggregate";
	/********** xml method constants end *********************/

}
