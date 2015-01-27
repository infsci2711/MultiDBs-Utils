package edu.pitt.sis.infsci2711.multidbs.utils;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;

public class JerseyJettyServer {
	
	private static final Logger logger = LogManager.getLogger(JerseyJettyServer.class.getName());
	
	private final int port;
	private final String providerPackages;
	private final String contextPath;
	
	public static final String DEFAULT_CONTEXT_PATH = "/rest/*";
	
	public JerseyJettyServer(final int port, final String providerPackages) {
		this.port = port;
		this.providerPackages = providerPackages;
		this.contextPath = DEFAULT_CONTEXT_PATH;
	}
	
	public JerseyJettyServer(final int port, final String providerPackages, final String contextPath) {
		this.port = port;
		this.providerPackages = providerPackages;
		this.contextPath = contextPath;
	}
	
	public void start() throws Exception {
		logger.info("Starting Jetty Server bound to port " + port);
		
		ServletHolder sh = new ServletHolder(ServletContainer.class);    

		sh.setInitParameter(ServerProperties.PROVIDER_PACKAGES, providerPackages);
		
		Server server = new Server(port);
        
        ServletContextHandler context = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
        context.addServlet(sh, contextPath);
        
        context.addFilter(ApiOriginFilter.class, contextPath, EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
        
        server.setStopAtShutdown(true);
        
        try {
       	 server.start();
       	 
       	 Runtime.getRuntime().addShutdownHook(
                    new Thread(new ShutdownSignalHandler(server))      
            );
       }
       catch (Exception e) {
       		logger.error("Failed to start  server", e);
       	
       		throw e;
       }
        
       server.join();
		
       logger.info("Jetty Server is shutdown");
	}
}

class ShutdownSignalHandler implements Runnable {
    
    private final Server _server;

    public ShutdownSignalHandler(final Server server) {
        this._server = server;
    }

    @Override
    public void run() {
       
        try {
            _server.stop();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
