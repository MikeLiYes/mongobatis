package com.github.mikeliyes.mongobatis.parsing;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.github.mikeliyes.mongobatis.exception.MessageException;
import com.github.mikeliyes.mongobatis.model.Configuration;
import com.github.mikeliyes.mongobatis.utils.XmlUtils;

public class XmlConfigurationParser {

	 private Document document;
	 
	 private XPath xpath;
	 
	 private Configuration configuration;

	 public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	
	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public XmlConfigurationParser(Reader reader) {
	    this.document = XmlUtils.createDocument(new InputSource(reader));  
	    commonConstructor();
	}
	
	private void commonConstructor() {
		XPathFactory factory = XPathFactory.newInstance();
	    this.xpath = factory.newXPath();
	}

	public XmlConfigurationParser(InputStream inputStream) {
	    this.document = XmlUtils.createDocument(new InputSource(new InputStreamReader(inputStream)));  
	    commonConstructor();
	}
	

	
	public Configuration parse() {
		Node node =  XmlUtils.evalNode(xpath, "/configuration", document);
	    parseConfiguration(node);
	    return configuration;
	  }

	  private void parseConfiguration(Node root) {
	    try {
	      
//	      dataSourceElement(root.getNodeValue());
//	      mapperElement(root.evalNode("mappers"));
	    } catch (Exception e) {
	      throw new MessageException("Error parsing SQL Mapper Configuration. Cause: " + e);
	    }
	  }
	
	 
}
