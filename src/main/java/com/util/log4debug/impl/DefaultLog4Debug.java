/* Generated by Together */

package com.util.log4debug.impl;

public class DefaultLog4Debug extends AbstractLog4DebugImpl {
  public DefaultLog4Debug(){}

  private Class loggedClass;
  public void setLoggedClass( Class loggedClass){
    this.loggedClass = loggedClass;
  }

  protected void log(int logLevel,String msg){
    System.out.println(
        new StringBuffer()
        .append("<Thread:")
        .append(Thread.currentThread().getName())
        .append('>')
        .append('<')
        .append(loggedClass)
        .append('>')
        .append('<')
        .append(logLevel == LOG_LEVEL_FATAL ? "FATAL" : 
          logLevel == LOG_LEVEL_SEVERE ? "SEVERE" : 
            logLevel == LOG_LEVEL_WARNING ? "WARN" : 
              logLevel == LOG_LEVEL_INFO ? "INFO" : "DEBUG")
        .append('>')
        .append(msg));
  }

  protected boolean isLoggable(int logLevel){
    return logLevel <= getLoggableLevel();
  }

  private int loggableLevel = -1;
  private int getLoggableLevel() {
    if (loggableLevel == -1) {
      String loggableLevelStr = Log4DebugProperties.getProperty("DefaultLog4Debug.implementation.logLevel","DEBUG");
      if (loggableLevelStr.equalsIgnoreCase("FATAL")) loggableLevel = LOG_LEVEL_SEVERE;
      else if (loggableLevelStr.equalsIgnoreCase("WARN")) loggableLevel = LOG_LEVEL_WARNING;
      else if (loggableLevelStr.equalsIgnoreCase("INFO")) loggableLevel = LOG_LEVEL_INFO;
      else loggableLevel = LOG_LEVEL_DEBUG;
    }
    return loggableLevel;
  }

}
