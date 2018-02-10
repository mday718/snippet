package com.backedrum;

import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;

import javax.management.MBeanServer;
import java.lang.management.ManagementFactory;

public class Start {

	public static void main(String[] args) throws Exception {
		System.setProperty("wicket.configuration", "development");

		Server server = new Server();

		HttpConfiguration http_config = new HttpConfiguration();
		http_config.setSecureScheme("https");
		http_config.setSecurePort(8443);
		http_config.setOutputBufferSize(32768);

		ServerConnector http = new ServerConnector(server, new HttpConnectionFactory(http_config));
		http.setPort(8080);
		http.setIdleTimeout(1000 * 60 * 60);

		server.addConnector(http);

		WebAppContext bb = new WebAppContext();
		bb.setServer(server);
		bb.setContextPath("/");
		bb.setWar("src/main/webapp");

		// uncomment the next two lines if you want to start Jetty with WebSocket (JSR-356) support
		// you need org.apache.wicket:wicket-native-websocket-javax in the classpath!
		// ServerContainer serverContainer = WebSocketServerContainerInitializer.configureContext(bb);
		// serverContainer.addEndpoint(new WicketServerEndpointConfig());

		// uncomment next line if you want to test with JSESSIONID encoded in the urls
		// ((AbstractSessionManager)
		// bb.getSessionHandler().getSessionManager()).setUsingCookies(false);

		server.setHandler(bb);

		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		MBeanContainer mBeanContainer = new MBeanContainer(mBeanServer);
		server.addEventListener(mBeanContainer);
		server.addBean(mBeanContainer);

		try
		{
			server.start();
			server.join();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(100);
		}
	}
}
