package com.github.mikeliyes.mongobatis.session;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import com.github.mikeliyes.mongobatis.model.Configuration;
import com.github.mikeliyes.mongobatis.parsing.XmlConfigurationParser;

public class ShellSessionFactoryBuilder {
	
	public ShellSessionFactory build(Reader reader) {
		try {
	    	XmlConfigurationParser parser = new XmlConfigurationParser(reader);
	      return build(parser.parse());
	    } finally {
	      try {
	    	  reader.close();
	      } catch (IOException e) {
	      }
	    }
	}

	public ShellSessionFactory build(InputStream inputStream) {
	    try {
	    	XmlConfigurationParser parser = new XmlConfigurationParser(inputStream);
	      return build(parser.parse());
	    } finally {
	      try {
	        inputStream.close();
	      } catch (IOException e) {
	      }
	    }
    }

	public ShellSessionFactory build(Configuration config) {
	    return new ShellSessionFactory(config);
	}
}
