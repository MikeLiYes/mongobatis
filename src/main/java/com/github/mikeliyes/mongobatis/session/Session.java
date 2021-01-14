package com.github.mikeliyes.mongobatis.session;

import com.github.mikeliyes.mongobatis.executor.Executor;
import com.github.mikeliyes.mongobatis.model.Configuration;

public class Session {

	private final Configuration configuration;
	private final Executor executor;
	
	public Configuration getConfiguration() {
		return configuration;
	}

	public Executor getExecutor() {
		return executor;
	}

	public Session(Configuration configuration,Executor executor) {
		this.configuration = configuration;
		this.executor = executor;
	}
	
	
}
