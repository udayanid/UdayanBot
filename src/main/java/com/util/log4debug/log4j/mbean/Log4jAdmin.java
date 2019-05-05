package com.util.log4debug.log4j.mbean;

import java.util.Map;

public class Log4jAdmin implements Log4jAdminMBean{
	public Log4jAdmin(){}
	
	// if find the key update the value to DEBUG 
	// if doesn't find the key insert key=DEBUG entry
	public String debug(String packageName){
    return addConfiguration(Log4jConfiguration.DEBUG, packageName); 
	}
	
	public String warn(String packageName){
    return addConfiguration(Log4jConfiguration.WARN, packageName); 
	}
	
	public String info(String packageName){
    return addConfiguration(Log4jConfiguration.INFO, packageName); 
	}
	
  public String error(String packageName){
    return addConfiguration(Log4jConfiguration.ERROR, packageName); 
  }
  
  public String fatal(String packageName){
    return addConfiguration(Log4jConfiguration.FATAL, packageName); 
  }

  public String off(String packageName){
    return addConfiguration(Log4jConfiguration.OFF, packageName); 
  }

  private String addConfiguration(Log4jConfiguration log4jConfiguration, String packageName) {
    try{
      if (!isPackageValid(packageName))
        throw new IllegalPackageException();
      return log4jConfiguration.add(packageName); 
    } catch(IllegalPackageException e){
      return "Invalid package name: "+packageName;
    } catch (Log4JEditorException e) {
      return "Can't add / update log level for package: "+packageName;
    }
  }
  
	public String removeTemporaryEntry(String packageName){
		String msg = null;
		try{
			if (!isPackageValid(packageName))
				throw new IllegalPackageException();
			msg = Log4jConfiguration.remove(packageName); 
		} catch(IllegalPackageException e){
			msg = "Invalid package name: "+packageName;
		} catch (Log4JEditorException e) {
			msg = "Can't remove log entry for package: "+packageName;
		}
		return msg;
	}
	
	public Map entries() throws Log4JEditorException{
		return Log4jConfiguration.entries();
	}
	
	private boolean isPackageValid(String packageName){
		String [] domains=packageName.split("\\.");
		if (domains.length<3 || !domains[0].equals("it") || !domains[1].equals("sella"))
			return false;
		return true;
	}

	public class IllegalPackageException extends  Log4JEditorException{
		private static final long serialVersionUID = 1L;

		public IllegalPackageException(){
			super();
		}
	}	
}