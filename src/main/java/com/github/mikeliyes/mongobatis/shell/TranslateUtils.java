package com.github.mikeliyes.mongobatis.shell;

import java.util.ArrayList;
import java.util.List;

public class TranslateUtils{
	
	public List<String> list  = new ArrayList<String>();
	
	public TranslateUtils() {
		list.add("fir");
		System.out.println("no");
	}
	
	public TranslateUtils(String str) {
		list.add("seconde");
		System.out.println("str");
	}
	
	public static void main(String[] args) {
		TranslateUtils ts = new TranslateUtils();
		ts.list.add("nihao");
		System.out.println(ts.hashCode()+":"+ts.list.hashCode());
		
		TranslateUtils ts2 = new TranslateUtils();
		ts2.list.add("nihao2");
		System.out.println(ts2.hashCode()+":"+ts2.list.hashCode());
		
//		ts.aggregate("dfasas");
		System.out.println("main");
	}
	
	public void aggregate(String shell) {
		System.out.println("aggregate");
	}
	

//	public List<DBObject> aggregate(String shell) {
//		
//		
//		return null;
//	}

}
