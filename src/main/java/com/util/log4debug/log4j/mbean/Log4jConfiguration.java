package com.util.log4debug.log4j.mbean;

import it.sella.util.Log4Debug;
import it.sella.util.Log4DebugFactory;
import it.sella.util.log4debug.log4j.Log4J;

import java.util.Map;

public class Log4jConfiguration {
  private static final Log4Debug log4Debug = Log4DebugFactory.getLog4Debug(Log4jConfiguration.class);

  public static Log4jConfiguration DEBUG  = new Log4jConfiguration("DEBUG");
	public static Log4jConfiguration WARN   = new Log4jConfiguration("WARN");
	public static Log4jConfiguration INFO   = new Log4jConfiguration("INFO");
  public static Log4jConfiguration ERROR = new Log4jConfiguration("ERROR");
  public static Log4jConfiguration FATAL = new Log4jConfiguration("FATAL");
  public static Log4jConfiguration OFF = new Log4jConfiguration("OFF");
	private static ConfigurationEditor editor;
	private String code;
	
	private Log4jConfiguration(String code){
		try{
			this.code = code;
			if(editor == null){
				synchronized(Log4jConfiguration.class){
					if(editor == null){
						String fileName = Log4J.configurationFileName();
						editor = Configurator.getEditor(fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()));
					}
				}
			}
		} catch(Log4JEditorException e){
			log4Debug.severeStackTrace(e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public String add(String packageName) throws Log4JEditorException{
		editor.addElement(packageName, code);
		return "Key successfully added: "+packageName+"="+code;
	}
	
	public static String remove(String packageName) throws Log4JEditorException{
		editor.removeElement(packageName);
		return "Key successfully removed: "+packageName;
	}
	
	public static Map entries() throws Log4JEditorException{
		return editor.entries();
	}
}