package com.util.log4debug.log4j.mbean;

import it.sella.util.Log4Debug;
import it.sella.util.Log4DebugFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.jaxen.JaxenException;
import org.jaxen.SimpleVariableContext;
import org.jaxen.XPath;
import org.jaxen.dom.DOMXPath;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Log4JXmlEditor implements ConfigurationEditor{
	private static final Log4Debug log4Debug = Log4DebugFactory.getLog4Debug(Log4JXmlEditor.class);
	private static final String fileName = "log4j.xml";
	private static final String filePath = "./";
	private static DocumentBuilder docBuilder;

	private static XPath loggerXpath;
	private static XPath paramXpath;
	
	public Log4JXmlEditor() throws Log4JEditorException{
		try{
			loggerXpath = new DOMXPath("//logger[@name=$packageName]");
			paramXpath = new DOMXPath("descendant::param[@name='MODIFIABLE' and @value='TRUE']");
			docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (JaxenException e){
			log4Debug.severeStackTrace(e);
			throw new Log4JEditorException(e.getMessage(), e);
		} catch (ParserConfigurationException e) {
			log4Debug.severeStackTrace(e);
			throw new Log4JEditorException(e.getMessage(), e);
		}
	}
	
	public Map entries() throws Log4JEditorException{
		Map entries = new HashMap();
		String packageName = "";
		String logLevel = "NONE";
		String modifiable = "FALSE";
		
		DocumentModel document = readFile();
		NodeList loggerList = document.root.getElementsByTagName("logger");
		for(int i = 0; i < loggerList.getLength(); i++){
			Element logger = (Element)loggerList.item(i);
			packageName = logger.getAttributeNode("name").getNodeValue();
			// at most there one priority element nested into logger element (see DTD)
			Element priority = (Element)logger.getElementsByTagName("level").item(0); 
			logLevel = priority.getAttributeNode("value").getNodeValue();
			NodeList paramList = priority.getElementsByTagName("param");
			for(int j = 0; j < paramList.getLength(); j++){
				Element param = (Element)paramList.item(j);
				if(param.getAttributeNode("name").getNodeValue().equals("MODIFIABLE"))
					modifiable = param.getAttributeNode("value").getNodeValue();
			}
			entries.put(packageName, new LogDetail(logLevel, modifiable.equals("TRUE")? true : false));
		}
		return entries;	
	}
		
	public void removeElement(String packageName) throws Log4JEditorException{
		DocumentModel document = readFile();
		searchElement(packageName, document);
		writeFile(document.doc);
	}
	
	public void addElement(String packageName, String logLevel) throws Log4JEditorException{
		DocumentModel document = readFile();
		searchElement(packageName, document);
		createLoggerNode(document.doc, document.root, packageName, logLevel);
		writeFile(document.doc);
	}
		
	private void createLoggerNode(Document doc, Element root, String packageName, String logLevel){
		Element logger = doc.createElement("logger");
		logger.setAttribute("name", packageName);
		
		Element level = doc.createElement("level");
		level.setAttribute("value", logLevel);
		
		Element param = doc.createElement("param");
		param.setAttribute("name", "MODIFIABLE");
		param.setAttribute("value", "TRUE");
		
		level.appendChild(param);
		logger.appendChild(level);
		root.insertBefore(logger, root.getElementsByTagName("root").item(0));
	}
	
	private void searchElement(String packageName, DocumentModel document) throws Log4JEditorException{
		try{
			List removedNodes = new ArrayList();
			
			SimpleVariableContext varContext = new SimpleVariableContext();
			varContext.setVariableValue("packageName", packageName);
			loggerXpath.setVariableContext(varContext);
			List loggerList = loggerXpath.selectNodes(document.doc); //all nodes with that match packageName
			for(Iterator it = loggerList.iterator(); it.hasNext();){
				Element e = (Element)it.next();
				List paramList = paramXpath.selectNodes(e);
				if(paramList.size() == 0)
					throw new Log4JEditorException("Can't remove logger for: "+packageName);
	            else
	               	removedNodes.add(e);
			}
			for(int i = 0; i < removedNodes.size(); i++){
				Element e = (Element)removedNodes.get(i);
				document.root.removeChild(e);
			}
		} catch(JaxenException e){
			log4Debug.severeStackTrace(e);
			throw new Log4JEditorException(e.getMessage(), e);
		}
	}
		
	private void writeFile(Document doc) throws Log4JEditorException{
		try {
			File oldFile = new File(filePath, fileName);
			if(!oldFile.renameTo(new File(filePath, fileName+".bak")))
				throw new Log4JEditorException("Can't rename file !!");
    		Transformer transf = TransformerFactory.newInstance().newTransformer();
    		transf.setOutputProperty(OutputKeys.INDENT, "yes");
    		transf.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "log4j.dtd");
    		transf.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(new File(filePath, fileName))));
       	} catch (TransformerException e) {
       		log4Debug.severeStackTrace(e);
       		throw new Log4JEditorException(e.getMessage(), e);
		} catch (IOException e) {
			log4Debug.severeStackTrace(e);
			throw new Log4JEditorException(e.getMessage(), e);
		}
	}
	
	private DocumentModel readFile() throws Log4JEditorException{
		try {
			Document doc = docBuilder.parse(new FileInputStream(new File(filePath, fileName)));
			Element root = doc.getDocumentElement();
			return new DocumentModel(doc, root);
		} catch (SAXException e) {
			log4Debug.severeStackTrace(e);
			throw new Log4JEditorException(e.getMessage(), e);
		} catch (IOException e) {
			log4Debug.severeStackTrace(e);
			throw new Log4JEditorException(e.getMessage(), e);
		}
	}
	
	private class DocumentModel{
		private Document doc;
		private Element root;
		
		private DocumentModel(Document doc,Element root){
			this.doc = doc;
			this.root = root;
		}
	}
}