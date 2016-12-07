package com.ofss.config.handler;

import java.util.List;

import com.ofss.config.model.ResourceConfig;
import com.ofss.config.provider.EntityManagerProvider;

public class CloudConfigHandler {

	public List<ResourceConfig> getAllResources(){
		
		return EntityManagerProvider.getEntityManager().createNamedQuery("ResourceConfig.getall",ResourceConfig.class).getResultList();
		
	}
	
	public ResourceConfig getResource(String resourceName){
		ResourceConfig resource=EntityManagerProvider.getEntityManager().find(ResourceConfig.class,resourceName);
        return resource;    	
	}
	
	
	public ResourceConfig addResource(ResourceConfig resource){
		
	
		EntityManagerProvider.beginTransaction();
		EntityManagerProvider.getEntityManager().persist(resource);
		EntityManagerProvider.commit();
		return resource;
	}
	
	public ResourceConfig updateResource(ResourceConfig resource){
		
		
		EntityManagerProvider.beginTransaction();
		EntityManagerProvider.getEntityManager().persist(resource);
		EntityManagerProvider.commit();
		return resource;
	}
	
	public ResourceConfig removeResource(String resourceName){
		
		EntityManagerProvider.beginTransaction();
		ResourceConfig resource=EntityManagerProvider.getEntityManager().find(ResourceConfig.class, resourceName);
		EntityManagerProvider.getEntityManager().remove(resource);
		EntityManagerProvider.commit();
		return resource;
		
				
	}
	
}
