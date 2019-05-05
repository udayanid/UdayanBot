package com.util.log4debug.log4j.mbean;

import java.util.Map;

public interface Log4jAdminMBean{
	public String debug(String packageName);
	public String warn(String packageName);
	public String info(String packageName);
  public String error(String packageName);
  public String fatal(String packageName);
  public String off(String packageName);
	public Map entries() throws Log4JEditorException;
}



