package com.github.mikeliyes.mongobatis.session;

import com.github.mikeliyes.mongobatis.executor.ShellExecutor;
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
//		ShellExecutor executor = configuration.newExecutor();
		return new Session(configuration, null);
	}
	
	
	
}
