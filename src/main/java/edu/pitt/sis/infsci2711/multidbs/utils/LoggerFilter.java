package edu.pitt.sis.infsci2711.multidbs.utils;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerFilter implements javax.servlet.Filter {
	 
	final Logger logger = LogManager.getLogger(LoggerFilter.class.getName());
	
	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response,
	      final FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse) response;
	    
	    logger.info(String.format("Begin %s %s", req.getMethod(), req.getRequestURI()));
	    
	    chain.doFilter(request, response);
	    
	    logger.info(String.format("End %s %s got %d status", req.getMethod(), req.getRequestURI(), res.getStatus()));
	}  

	@Override
	public void destroy() {
	}

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
	}
}