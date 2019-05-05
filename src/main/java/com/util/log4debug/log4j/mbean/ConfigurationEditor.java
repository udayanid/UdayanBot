package com.util.log4debug.log4j.mbean;

import java.util.Map;

public interface ConfigurationEditor {
	public Map entries() throws Log4JEditorException;
	public void removeElement(String packageName) throws Log4JEditorException;
	public void addElement(String packageName, String logLevel) throws Log4JEditorException;
}
