package com.github.mikeliyes.mongobatis.session;

import java.io.IOException;
import java.io.InputStream;

import com.github.mikeliyes.mongobatis.model.Configuration;
import com.github.mikeliyes.mongobatis.parsing.XmlConfigurationParser;

public class ShellSessionFactoryBuilder {

	public ShellSessionFactory build(InputStream inputStream, Configuration configuration) {
	    try {
	    	XmlConfigurationParser parser = new XmlConfigurationParser(inputStream);
	      return build(parser.parse());
	    } catch (Exception e) {
	      throw ExceptionFactory.wrapException("Error building SqlSession.", e);
	    } finally {
	      ErrorContext.instance().reset();
	      try {
	        inputStream.close();
	      } catch (IOException e) {
	        // Intentionally ignore. Prefer previous error.
	      }
	    }
	  }

	  public SqlSessionFactory build(Configuration config) {
	    return new DefaultSqlSessionFactory(config);
	  }
}
