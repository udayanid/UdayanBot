package com.util.log4debug.log4j.mbean;

public class LogDetail {
	private String logLevel;
	private boolean modifiable;
	
	public LogDetail(String logLevel, boolean modifiable){
		this.logLevel = logLevel;
		this.modifiable = modifiable;
	}

	public String toString(){
		return "logLevel="+logLevel+" - modifiable="+modifiable;
	}
}
