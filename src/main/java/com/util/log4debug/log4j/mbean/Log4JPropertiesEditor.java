package com.util.log4debug.log4j.mbean;

import it.sella.util.Log4Debug;
import it.sella.util.Log4DebugFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Log4JPropertiesEditor implements ConfigurationEditor{
	private static final Log4Debug log4Debug = Log4DebugFactory.getLog4Debug(Log4JPropertiesEditor.class);
	private static final String fileName = "log4j.properties";
	private static final String filePath = "./";
	
	private void closeFileResource(BufferedReader file){
		try{
			if(file != null)
				file.close();
		} catch(Throwable t ){
			t.printStackTrace();
		}
	}
	
	private void closeFileResource(BufferedWriter file){
		try{
			if(file != null)
				file.close();
		} catch(Throwable t ){
			t.printStackTrace();
		}
	}
	
	public Map entries() throws Log4JEditorException{
		Map entries = new HashMap();
		BufferedReader file = null;
		try{
			int startModifiableSection = -1;
			int stopModifiableSection = -1;

			log4Debug.debug("<entries> reading entries for: ", fileName);
			file = new BufferedReader(new FileReader(filePath+fileName));
			String line = null;
			for(int idx = 0; (line = file.readLine()) != null; idx++){
				log4Debug.debug("<entries> line read: ", line);
				if(line.indexOf("#<Temporary>") != -1)
					startModifiableSection = idx;
				if(line.indexOf("#</Temporary>") != -1)
					stopModifiableSection = idx;
				
				if(line.trim().startsWith("log4j.logger.")){
					String[] items = line.split("=");
					//if((start != -1 && idx > start) && (stop == -1 || idx < stop))
					if(startModifiableSection == -1 || (stopModifiableSection != -1 && idx > stopModifiableSection))
						entries.put(items[0].replaceAll("log4j.logger.", ""), new LogDetail(items[1], false));					
					else
						entries.put(items[0].replaceAll("log4j.logger.", ""), new LogDetail(items[1], true));
				}
			}
		} catch(IOException e){
			log4Debug.severeStackTrace(e);
			throw new Log4JEditorException(e.getMessage(), e);
		} finally{
			closeFileResource(file);
		}
		return entries;	
	}
	
	public void addElement(String packageName, String logLevel) throws Log4JEditorException{
		log4Debug.debug("<addElement> going to get modifiable elements ...");
		DocumentModel document = searchElement(packageName);
		List logEntry = new ArrayList();
		log4Debug.debug("<addElement> adding log entry: ",packageName,"=",logLevel.toUpperCase());
		logEntry.add("log4j.logger."+packageName+"="+logLevel.toUpperCase());
		document.rows.addAll(document.startModifiableSection+1, logEntry);
		writeFile(document.rows);
		
	}
	
	public void removeElement(String packageName) throws Log4JEditorException{
		log4Debug.debug("<removeElement> going to get modifiable elements ...");
		DocumentModel document = searchElement(packageName);
		log4Debug.debug("<removeElement> removed log entry for: ",packageName);
		writeFile(document.rows);
	}
	
	private DocumentModel searchElement(String packageName) throws Log4JEditorException{
		BufferedReader file = null;
		
		List rows = new ArrayList();
		int startModifiableSection = -1;
		int stopModifiableSection = -1;
		try{
			file = new BufferedReader(new FileReader(filePath+fileName));
			String line = null;
		
			for(int idx = 0; (line = file.readLine()) != null; idx++){
				if(line.indexOf("#<Temporary>") != -1)
					startModifiableSection = idx;
				if(line.indexOf("#</Temporary>") != -1)
					stopModifiableSection = idx;
				
				if(line.trim().startsWith("log4j.logger."+packageName)){
					if(startModifiableSection == -1 || (stopModifiableSection != -1 && idx > stopModifiableSection))
						throw new Log4JEditorException("Can't remove logger for: "+packageName);
				} else{
					rows.add(line);
				}				
			}
		} catch(IOException e){
			log4Debug.severeStackTrace(e);
			throw new Log4JEditorException(e.getMessage(), e);
		} finally{
			closeFileResource(file);
		}
		return new DocumentModel(rows, startModifiableSection, stopModifiableSection);
	}
	
	private void writeFile(List rows) throws Log4JEditorException{
		BufferedWriter newFile = null;
		try {
			log4Debug.debug("<writeFile> renaming file ...");
			File oldFile = new File(filePath, fileName);
			if(!oldFile.renameTo(new File(filePath, fileName+".bak")))
				throw new Log4JEditorException("Can't rename file: "+filePath+fileName);
    		
			log4Debug.debug("<writeFile> file renamed ...");
			newFile = new BufferedWriter(new FileWriter(filePath+fileName));
			for(Iterator it = rows.iterator(); it.hasNext();){
				newFile.write(((String)it.next())+"\n");
			}
			log4Debug.debug("<writeFile> file updated ...");
       	} catch (IOException e) {
       		log4Debug.severeStackTrace(e);
			throw new Log4JEditorException(e.getMessage(), e);
		} finally{
			closeFileResource(newFile);
		}
	}
	
	private class DocumentModel{
		// 	index of Temporary section
		private int startModifiableSection = -1;
		private int stopModifiableSection = -1;
		private List rows = new ArrayList();

		private DocumentModel(){}
		
		private DocumentModel(List rows, int startModifiableSection, int stopModifiableSection){
			this.rows = rows;
			this.startModifiableSection = startModifiableSection; 
			this.stopModifiableSection = stopModifiableSection; 
		}
	}
}