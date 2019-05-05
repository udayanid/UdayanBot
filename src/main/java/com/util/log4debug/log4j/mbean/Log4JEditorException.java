package com.util.log4debug.log4j.mbean;

public class Log4JEditorException extends Exception{
	public Log4JEditorException(){
		super();
	}
	
	public Log4JEditorException(String msg){
		super(msg);
	}
	
	public Log4JEditorException(String msg, Exception e){
		super(msg, e);
	}
}
