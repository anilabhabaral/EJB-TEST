package com.org;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;



public class Client {

	 private static final String REMOTE_HTTP = "remote+http";

	   private static final int REMOTE_HTTP_PORT = 8080;

	   private static final String USERNAME = "quickstartUser";
	   private static final String PASSWORD = "quickstartPwd1!";
	  
	   public static void main(String[] args)  {

	      String host = "localhost";	
			
	      try {
	         System.out.printf("Using: %s://%s:%d user: %s pass: %s\n", REMOTE_HTTP, host, REMOTE_HTTP_PORT, USERNAME, PASSWORD);
	         System.out.println("------------------------------------------------------------------------------");
	         Context ctx = getInitialContext(REMOTE_HTTP, host, REMOTE_HTTP_PORT, USERNAME, PASSWORD);	
		 Hello hello = (Hello) ctx.lookup( "ejb:/EJB-TEST/Test!com.org.Hello");

	         System.out.println("\n\n\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
	         System.out.println(hello.hello());
	         System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
	         System.out.flush();
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	   }
	
	
	 public static Context getInitialContext(String protocol, String host, Integer port, String username, String password)  throws NamingException {
	      Properties env = new Properties();
	      env.put(Context.INITIAL_CONTEXT_FACTORY,  "org.wildfly.naming.client.WildFlyInitialContextFactory");
	      env.put(Context.PROVIDER_URL, String.format("%s://%s:%d", protocol, host, port));
	      if(username != null && password != null) {
	         env.put(Context.SECURITY_PRINCIPAL, username);
	         env.put(Context.SECURITY_CREDENTIALS, password);
	      }
	      return new InitialContext(env);
		}
	}


