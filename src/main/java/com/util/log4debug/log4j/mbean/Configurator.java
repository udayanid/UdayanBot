package com.util.log4debug.log4j.mbean;

public class Configurator {
	public static ConfigurationEditor getEditor(String extension) throws Log4JEditorException{
		ConfigurationEditor editor = null;
		if(extension.equals("properties"))
			editor = new Log4JPropertiesEditor();
		if(extension.equals("xml"))
			editor = new Log4JXmlEditor();
		return editor;
	}
}
