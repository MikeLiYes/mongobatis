package com.github.mikeliyes.mongobatis.session;

import com.github.mikeliyes.mongobatis.model.Configuration;

public class Session {
	
	private final Configuration configuration;
    
    public Session(Configuration configuration) {
    	this.configuration = configuration;
	}
    
    public <T> T getDao(Class<T> type) {
      return configuration.getDao(type);
    }

}
