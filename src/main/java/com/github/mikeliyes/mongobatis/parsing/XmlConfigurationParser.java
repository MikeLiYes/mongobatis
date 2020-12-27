package com.github.mikeliyes.mongobatis.parsing;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.ibatis.builder.BuilderException;
import org.apache.ibatis.parsing.XNode;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.github.mikeliyes.mongobatis.exception.MessageException;
import com.github.mikeliyes.mongobatis.model.Configuration;

public class XmlConfigurationParser {

	 private Document document;

	 public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public XmlConfigurationParser(Reader reader) {
	    this.document = createDocument(new InputSource(reader));  
	}
	
	public XmlConfigurationParser(InputStream inputStream) {
	    this.document = createDocument(new InputSource(new InputStreamReader(inputStream)));  
	}
	
	private Document createDocument(InputSource inputSource) {
	    try {
	      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	      factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
	      factory.setValidating(true);

	      factory.setNamespaceAware(false);
	      factory.setIgnoringComments(true);
	      factory.setIgnoringElementContentWhitespace(false);
	      factory.setCoalescing(false);
	      factory.setExpandEntityReferences(true);

	      DocumentBuilder builder = factory.newDocumentBuilder();
	      return builder.parse(inputSource);
	    } catch (Exception e) {
	      throw new MessageException("Error creating XmlConfigurationParser document instance, Cause: "+e);
	    }
	}
	
	public NodeList evalNode(String expression) {
		NodeList nodeList = document.getElementsByTagName(expression);
	    return nodeList;
	}
	
	public Configuration parse() {
	    parseConfiguration(parser.evalNode("/configuration"));
	    return configuration;
	  }

	  private void parseConfiguration(XNode root) {
	    try {
	      //issue #117 read properties first
	      propertiesElement(root.evalNode("properties"));
	      Properties settings = settingsAsProperties(root.evalNode("settings"));
	      loadCustomVfs(settings);
	      loadCustomLogImpl(settings);
	      typeAliasesElement(root.evalNode("typeAliases"));
	      pluginElement(root.evalNode("plugins"));
	      objectFactoryElement(root.evalNode("objectFactory"));
	      objectWrapperFactoryElement(root.evalNode("objectWrapperFactory"));
	      reflectorFactoryElement(root.evalNode("reflectorFactory"));
	      settingsElement(settings);
	      // read it after objectFactory and objectWrapperFactory issue #631
	      environmentsElement(root.evalNode("environments"));
	      databaseIdProviderElement(root.evalNode("databaseIdProvider"));
	      typeHandlerElement(root.evalNode("typeHandlers"));
	      mapperElement(root.evalNode("mappers"));
	    } catch (Exception e) {
	      throw new BuilderException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
	    }
	  }
	
	 
}
