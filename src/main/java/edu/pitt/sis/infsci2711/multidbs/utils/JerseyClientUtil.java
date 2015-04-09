package edu.pitt.sis.infsci2711.multidbs.utils;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import com.sun.jersey.api.json.JSONConfiguration;

public class JerseyClientUtil {
	private static Client getClient() {
		ClientConfig clientConfit = new ClientConfig();
		clientConfit.property(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = ClientBuilder.newClient(clientConfit);
		return client;
	}
	
	/**
	 * Will send get request to the address specified by the parameter.
	 * 
	 * @param restContext the context of the rest (e.g. the base part of the REST API URL)
	 * @param restResource the resource name with params. MAKE SURE it starts with "/"
	 * @return
	 */
	public static Response doGet(final String restContext,  final String restResource) {
		
		Client client = getClient();
		
		WebTarget target = client.target(restContext + restResource);
		   
		Response response = target.request(MediaType.APPLICATION_JSON_TYPE).get();
		
		return response;
	}
	
	/**
	 * Will send post request to the address specified by the parameter.
	 * 
	 * @param restContext the context of the rest (e.g. the base part of the REST API URL)
	 * @param restResource the resource name with params. MAKE SURE it starts with "/"
	 * @param data the data to send (your view model)
	 * @return
	 */
	public static Response doPost(final String restContext,  final String restResource, final Object data) {
		
		Client client = getClient();
		
		WebTarget target = client.target(restContext + restResource);
		   
		Response response = target.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(data, MediaType.APPLICATION_JSON_TYPE));
		
		return response;
	}
	
	/**
	 * Will send post request to the address specified by the parameter.
	 * 
	 * @param restContext the context of the rest (e.g. the base part of the REST API URL)
	 * @param restResource the resource name with params. MAKE SURE it starts with "/"
	 * @param data the data to send (your view model)
	 * @return
	 */
	public static Response doPut(final String restContext,  final String restResource, final Object data) {
		
		Client client = getClient();
		
		WebTarget target = client.target(restContext + restResource);
		   
		Response response = target.request(MediaType.APPLICATION_JSON_TYPE).put(Entity.entity(data, MediaType.APPLICATION_JSON_TYPE));
		
		return response;
	}
}
