package com.org;

import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;

/*
Run this client using the following command:
****************
java -Djavax.net.ssl.trustStore=client.keystore -Djavax.net.ssl.trustStorePassword=password -cp bin/client/jboss-client.jar:standalone/deployments/testejb01.jar com.org.Client_SSL
****************

 */

public class Client_SSL {

	private static final String REMOTE_HTTPS = "remote+https";

	private static final int REMOTE_HTTPS_PORT = 8443;

	private static final String USERNAME = "quickstartUser";
	private static final String PASSWORD = "quickstartPwd1!";

	public static void main(String[] args) throws Exception {

		String host = "localhost";
		final String lookup = "ejbapp/Hello!com.jboss.examples.ejb.Hello";

		try {
			System.out.printf("Using: %s://%s:%d user: %s pass: %s\n", REMOTE_HTTPS, host, REMOTE_HTTPS_PORT, USERNAME, PASSWORD);
			System.out.println("------------------------------------------------------------------------------");
			Context ctx = getInitialContext(REMOTE_HTTPS, host, REMOTE_HTTPS_PORT, USERNAME, PASSWORD);
			Hello ejb = (Hello) ctx.lookup(lookup);
			System.out.printf("hello: %s\n", ejb.hello());
			System.out.println("------------------------------------------------------------------------------");
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

