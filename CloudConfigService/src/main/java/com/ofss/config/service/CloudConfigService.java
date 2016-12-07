package com.ofss.config.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ofss.config.handler.CloudConfigHandler;
import com.ofss.config.model.ResourceConfig;
import com.ofss.util.common.FCLiteUtility;

@Path("configuration")
public class CloudConfigService {
	
	CloudConfigHandler handler=new CloudConfigHandler();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<ResourceConfig> getAllResourceConfig(){
		return handler.getAllResources();
	}
	
	@GET
	@Path("/{resourceName}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public ResourceConfig getResourceConfig(@PathParam("resourceName")String name){
		
		return handler.getResource(name);
	}
	
	@POST
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response createResourceConfig(ResourceConfig resource){
		
		ResourceConfig resourceConfig=handler.addResource(resource);
		return FCLiteUtility.build200Response(resourceConfig);
	}
	
	@PUT
	@Path("/{resourceName}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updateResourceConfig(@PathParam("resourceName")String name,ResourceConfig resource){
		resource.setResource_type(name);
		ResourceConfig resourceConfig=handler.updateResource(resource);
		return FCLiteUtility.build200Response(resourceConfig);
	}
	
	@DELETE
	@Path("/{resourceName}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response deleteResourceConfig(@PathParam("resourceName")String name){
		ResourceConfig resourceConfig=handler.removeResource(name);
		return FCLiteUtility.build200Response(resourceConfig);
	}
	
	
    
}
