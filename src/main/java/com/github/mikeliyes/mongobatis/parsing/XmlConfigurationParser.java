package com.github.mikeliyes.mongobatis.parsing;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.github.mikeliyes.mongobatis.model.Configuration;
import com.github.mikeliyes.mongobatis.model.DataSource;
import com.github.mikeliyes.mongobatis.utils.XmlUtils;

public class XmlConfigurationParser {

	 private Document document;
	 
	 private XPath xpath;
	 
	 private static Configuration configuration;
	 
	 static{
		 configuration = new Configuration();
	 }

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
		XmlConfigurationParser.configuration = configuration;
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
		NodeList nodeList =  XmlUtils.evalNodeList(xpath, "configuration", document);
	    parseConfiguration(nodeList);
	    return configuration;
    }

	private void parseConfiguration(NodeList nodeList) {
	     for (int i = 0; i < nodeList.getLength(); i++) {
				 Node node = nodeList.item(i); 
				 dataSourceProperty(node);
		 }
	}

	private void dataSourceProperty(Node dsNode) {

		DataSource ds = null;
		if (configuration.getDataSource() == null) {
			ds = new DataSource();
		}
	
		dataSourceProperty(dsNode, ds);
		dataSourceMapper(dsNode,ds);
		
		configuration.setDataSource(ds);	
	}

	private void dataSourceProperty(Node dsNode, DataSource ds) {
		NodeList nodes = XmlUtils.evalNodeList(xpath, "dataSource/property", dsNode);
		
		for (int i = 0; i < nodes.getLength(); i++) {
		    Node node = nodes.item(i);  
		    dataSourceProperty(ds, node);
		}
	}

	private void dataSourceProperty(DataSource ds, Node node) {
		String name = (String)XmlUtils.evalString(xpath, "name", node);
		String value = (String)XmlUtils.evalString(xpath, "value", node);
		
		if ("url".equalsIgnoreCase(name)) {
			ds.setUrl(value);
		}else if ("username".equalsIgnoreCase(name)){
			ds.setName(value);
		}else if ("password".equalsIgnoreCase(name)){
			ds.setPassword(value);
		}
	}
	
	private void dataSourceMapper(Node dsNode,DataSource ds) {
        NodeList nodes = XmlUtils.evalNodeList(xpath, "mappers/mapper", dsNode);
		
		for (int i = 0; i < nodes.getLength(); i++) {
		    Node node = nodes.item(i);  
		    resourceMapper(ds, node);
		}
	}

	private void resourceMapper(DataSource ds, Node node) {
		String name = (String)XmlUtils.evalString(xpath, "name", node);
		String resource = (String)XmlUtils.evalString(xpath, "resource", node);
		
		ds.setMapper(name, resource);
	}
	
	 
}
