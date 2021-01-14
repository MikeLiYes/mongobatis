package com.github.mikeliyes.mongobatis.session;

import com.github.mikeliyes.mongobatis.executor.Executor;
import com.github.mikeliyes.mongobatis.model.Configuration;

public class ShellSessionFactory {

	private Configuration configuration;
	
	public ShellSessionFactory(Configuration configuration) {
		    this.configuration = configuration;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
	public Session openSession() {
		final Executor executor = configuration.newExecutor();
		return new Session(configuration, executor);
	}
	
}
