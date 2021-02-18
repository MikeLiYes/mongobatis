package com.github.mikeliyes.mongobatis.parsing;

import java.io.IOException;
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

import com.github.mikeliyes.mongobatis.io.Resources;
import com.github.mikeliyes.mongobatis.model.Configuration;
import com.github.mikeliyes.mongobatis.model.Constants;
import com.github.mikeliyes.mongobatis.model.DataSource;
import com.github.mikeliyes.mongobatis.model.Mapper;
import com.github.mikeliyes.mongobatis.model.ShellMethod;
import com.github.mikeliyes.mongobatis.shell.SplitShellUtils;
import com.github.mikeliyes.mongobatis.utils.MongoUtils;
import com.github.mikeliyes.mongobatis.utils.StringUtils;
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

	public XmlConfigurationParser(InputStream inputStream) {
	    this.document = XmlUtils.createDocument(new InputSource(new InputStreamReader(inputStream)));  
	    commonConstructor();
	}
	
	private void commonConstructor() {
		configuration = new Configuration();
		XPathFactory factory = XPathFactory.newInstance();
	    this.xpath = factory.newXPath();
	}
	
	public Configuration parse() {
		
		parseDataSource();
		parseMappers();
		
	    return configuration;
    }

	/**       parse config.xml to Object start       **/
	private void parseMappers() {
		Node mappers = XmlUtils.evalNode(xpath, "configuration/mappers", this.document);
		
		NodeList childNodes = mappers.getChildNodes();
		for (int j = 0; j <childNodes.getLength() ; j++) {
            if (childNodes.item(j).getNodeType()==Node.ELEMENT_NODE) {
            	Element mapperElement = (Element)childNodes.item(j);
            	String resource = mapperElement.getAttribute("resource");
            	
            	Mapper mapper = new Mapper();
            	mapper.setResource(resource);
            	
            	parseMappersXml(resource,mapper);
            	
            	configuration.setMapper(mapper);
            }
		} 
		
	}
	
	private void parseDataSource() {
		NodeList nodes = XmlUtils.evalNodeList(xpath, "configuration/environment/dataSource", this.document);
        
        DataSource ds = new DataSource();
		
		for (int i = 0; i < nodes.getLength(); i++) {
		    Node node = nodes.item(i);  
		    configDsId(ds, node);
		    configDataSource(ds, node);
	   }
 
	}

	private void configDsId(DataSource ds, Node node) {
		if (node.getNodeType()==Node.ELEMENT_NODE) {
			Element dataSource = (Element)node;
			String id = dataSource.getAttribute("id");
			String isDefault = dataSource.getAttribute("isDefault");
			ds.setId(id);
			
			if (StringUtils.isNotBlank(isDefault)
					|| "true".equalsIgnoreCase(isDefault.trim())) {
				configuration.setDataSourceEnv(id);
			}
		}
	}

	private void configDataSource(DataSource ds, Node node) {
		
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
        		}else if ("dbname".equalsIgnoreCase(name)) {
        			ds.setDbName(value);
        		}
            }
	    }
		
		if (StringUtils.isNotBlank(configuration.getDataSourceEnv())) {
			MongoUtils.initMongoUtils(ds);
		}
		
		configuration.setDataSource(ds);
	}
	/**       parse config.xml to Object  end      **/
	
	/**       parse dao.xml to Object start       **/
	private void parseMappersXml(String resource,Mapper mapper) {
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document mapperDoc = XmlUtils.createDocument(new InputSource(reader));
		
		//parse namespace
		Node mapperNode = XmlUtils.evalNode(xpath, Constants.DAO, mapperDoc);
		
		if (mapperNode != null && mapperNode.getNodeType()==Node.ELEMENT_NODE) {
        	Element mapperElement = (Element)mapperNode;
        	String namespace = mapperElement.getAttribute(Constants.NAME_SPACE);
        	mapper.setNameSpace(namespace);
		}	
		
		//parse aggregate
		NodeList childNodes = mapperNode.getChildNodes();
		for (int j = 0; j <childNodes.getLength() ; j++) {
			if (childNodes.item(j).getNodeType()==Node.ELEMENT_NODE) {
				Element shellEle = (Element)childNodes.item(j);
                parseShellNode(shellEle, mapper);
			}
		}    
		
	}

	private void parseShellNode(Element element,Mapper mapper) {
		
			String shellName = element.getNodeName();
			
			if (StringUtils.isNotBlank(shellName)
					&& shellName.equalsIgnoreCase(Constants.METHOD_TYPE_AGGREGATE)) {
				
				ShellMethod method = new ShellMethod();
				
				String id = element.getAttribute("id");
				method.setId(id);
				
				method.setFullMethodName(mapper.getNameSpace()+"."+method.getId());
				
				String shell = element.getTextContent().replace("\n", "").replace("\t", "").replaceAll(" ", "").toLowerCase();
				method.setShell(shell);
				
				method.setNameSpace(mapper.getNameSpace());
				
				method.setMethodType(Constants.METHOD_TYPE_AGGREGATE);
				
				String collectionName = StringUtils.getSubStringExclude(shell, Constants.COLLECTION_NAME_START, Constants.COLLECTION_NAME_END);
				method.setCollectionName(collectionName);
				
				SplitShellUtils.splitMethodShell(method);
				
				mapper.setShellMethod(method);
				
				configuration.setShellMethod(method);
				
			}
		
	}
	
	/**       parse dao.xml to Object end       **/
}
