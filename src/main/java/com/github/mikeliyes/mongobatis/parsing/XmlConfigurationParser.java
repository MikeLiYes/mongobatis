package com.github.mikeliyes.mongobatis.parsing;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
		 XmlConfigurationParser.configuration = new Configuration();
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
	    parseConfiguration();
	    return configuration;
    }

	private void parseConfiguration() {
		dataSourceElement();
	}

	private void dataSourceElement() {
		NodeList nodes = XmlUtils.evalNodeList(xpath, "configuration/environment/dataSource", this.document);
        dataSourceProperty(nodes);
	}

	private void dataSourceProperty(NodeList nodes) {

		DataSource ds = new DataSource();
				
		for (int i = 0; i < nodes.getLength(); i++) {
		    Node node = nodes.item(i);  
		    configDataSourceByNode(ds, node);
	   }
		
	   configuration.addDataSource(ds);
	}

	private void configDataSourceByNode(DataSource ds, Node node) {
		
		NodeList childNodes = node.getChildNodes();
		for (int j = 0; j <childNodes.getLength() ; j++) {
            if (childNodes.item(j).getNodeType()==Node.ELEMENT_NODE) {
            	Element property = (Element)childNodes.item(j);
            	String name = property.getAttribute("name");
            	String value = property.getAttribute("value");
            	if ("url".equalsIgnoreCase(name)) {
        			ds.setUrl(value);
        		}else if ("username".equalsIgnoreCase(name)){
        			ds.setName(value);
        		}else if ("password".equalsIgnoreCase(name)){
        			ds.setPassword(value);
        		}
            }
	    }
	}
}
