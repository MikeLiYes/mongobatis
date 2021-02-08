package com.github.mikeliyes.mongobatis.session;

import com.github.mikeliyes.mongobatis.executor.ShellExecutor;
import com.github.mikeliyes.mongobatis.model.Configuration;

public class Session {
	
	private final Configuration configuration;
    private final ShellExecutor executor;
    
    public Session(Configuration configuration, ShellExecutor executor) {
    	this.configuration = configuration;
        this.executor = executor;
	}
    
    public <T> T getMapper(Class<T> type) {
      return configuration.getMapper(type);
    }

}
