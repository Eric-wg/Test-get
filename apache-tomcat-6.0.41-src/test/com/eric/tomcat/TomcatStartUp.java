package com.eric.tomcat;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.core.StandardService;
import org.apache.catalina.startup.HostConfig;

public class TomcatStartUp {

	public static void main(String[] args) throws Exception {
		System.setProperty("catalina.base",
				"C:/work/eclipse_workspace/apache-tomcat-6.0.41-src");

		Connector connector = new Connector("HTTP/1.1");
		connector.setPort(8080);

		StandardHost standardHost = new StandardHost();
		standardHost.setName("localhost");
		standardHost.setAppBase("webapps");

		standardHost.addLifecycleListener(new HostConfig());

		StandardEngine standardEngine = new StandardEngine();
		standardEngine.setName("Catalina");

		standardEngine.addChild(standardHost);
		standardEngine.setDefaultHost("localhost");

		StandardService standardService = new StandardService();
		standardService.setName("Catalina");

		standardService.addConnector(connector);

		standardService.setContainer(standardEngine);

		standardService.start();
		
		while (true) {
			Thread.sleep(60000);
			System.out.println("still active");
		}
	}
}
