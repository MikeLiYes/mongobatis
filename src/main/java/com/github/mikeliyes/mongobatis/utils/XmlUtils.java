package com.github.mikeliyes.mongobatis.utils;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.github.mikeliyes.mongobatis.exception.MessageException;

public class XmlUtils {

	public static Document createDocument(InputSource inputSource) {
	    try {
	      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	      factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
//	      factory.setValidating(true);

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
	
	public static String evalString(XPath xpath,String expression,Object document) {	    
	    try {
		      return (String) xpath.evaluate(expression,document, XPathConstants.STRING);
		} catch (Exception e) {
		      throw new MessageException("Error evaluating Node path.  Cause: " + e);
		}
	    
   }
	
	public static Node evalNode(XPath xpath,String expression,Object document) {	    
	    try {
		      return (Node) xpath.evaluate(expression,document, XPathConstants.NODE);
		} catch (Exception e) {
		      throw new MessageException("Error evaluating Node path.  Cause: " + e);
		}
	    
	}
	
	public static NodeList evalNodeList(XPath xpath,String expression,Object document) {	    
	    try {
		      return (NodeList) xpath.evaluate(expression,document, XPathConstants.NODESET);
		} catch (Exception e) {
		      throw new MessageException("Error evaluating NodeList path.  Cause: " + e);
		}
	    
	}
	
}
