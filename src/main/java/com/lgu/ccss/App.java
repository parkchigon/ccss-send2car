package com.lgu.ccss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
@EnableAspectJAutoProxy
public class App 
{
	private final static Logger logger = LoggerFactory.getLogger(App.class);
	
	public static ApplicationContext ctx;
	public static String serverID = null;
	private final static String ENV_SERVER_ID = "SERVER_ID";
	
    public static void main( String[] args )
    {
    	if( System.getProperty("SERVER_ID") == null )
    	{
    		logger.error("Environment Value Not Set! Check " + ENV_SERVER_ID);
    		System.exit(0);
    	}
    	
    	serverID = System.getProperty("SERVER_ID");
    	
    	ctx = new ClassPathXmlApplicationContext("META-INF/spring/applicationContext.xml");
    	
    }
}
