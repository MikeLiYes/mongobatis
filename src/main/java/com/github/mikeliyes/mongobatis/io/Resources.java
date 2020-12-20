package com.github.mikeliyes.mongobatis.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

public class Resources {
	
	private static ClassLoader[] classLoaders =  new ClassLoader[]{
	        Thread.currentThread().getContextClassLoader(),
	        Resources.class.getClassLoader(),
	        ClassLoader.getSystemClassLoader()};
	
	/**
	   * Returns a resource on the classpath as a Reader object
	   *
	   * @param resource The resource to find
	   * @return The resource
	   * @throws java.io.IOException If the resource cannot be found or read
	   */
	  public static Reader getResourceAsReader(String resource) throws IOException {
		URL path = Thread.currentThread().getContextClassLoader().getResource("");
	    Reader reader = getResourceAsReader(resource,null); 
	    return reader;
	  }
	
	/**
	   * Returns a resource on the classpath as a Reader object
	   *
	   * @param resource The resource to find
	   * @return The resource
	   * @throws java.io.IOException If the resource cannot be found or read
	   */
	  public static Reader getResourceAsReader(String resource,Charset charset) throws IOException {
	    Reader reader;
	    if (charset == null) {
	      reader = new InputStreamReader(getResourceAsStream(resource));
	    } else {
	      reader = new InputStreamReader(getResourceAsStream(resource), charset);
	    }
	    return reader;
	  }
	  
	  /**
	   * Returns a resource on the classpath as a Stream object
	   *
	   * @param resource The resource to find
	   * @return The resource
	   * @throws java.io.IOException If the resource cannot be found or read
	   */
	  public static InputStream getResourceAsStream(String resource) throws IOException {
		  InputStream in = Resources.getResourceAsStreamFrom(resource);
		    if (in == null) {
		      throw new IOException("Could not find resource " + resource);
		    }
		    return in;
	  }
	  
	  /**
	   * Try to get a resource from a group of classloaders
	   *
	   * @param resource    - the resource to get
	   * @return the resource or null
	   */
	  private static InputStream getResourceAsStreamFrom(String resource) {
		  for (ClassLoader classLoader : classLoaders) {
		      if (null != classLoader) {
	
		        InputStream returnValue = classLoader.getResourceAsStream(resource);
	
		        if (null == returnValue) {
		          returnValue = classLoader.getResourceAsStream("/" + resource);
		        }
	
		        if (null != returnValue) {
		          return returnValue;
		        }
		      }
		  }
	    return null;
	  }

}
