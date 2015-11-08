package com.rtn.drm.test.resource.impl;

import java.util.Dictionary;

import com.rtn.drm.test.resource.service.ResourceIF;

public class Resource implements ResourceIF
{
	public String resourceId;
	
	@Override
   public String getResourceId()
   {
		System.out.println(this.getClass().getName() + " GET RESOURCE ID CALLED");
	   return resourceId;
   }
	public void setResourceId(String resourceId)
	{
		System.out.println(this.getClass().getName() + " SET RESOURCE ID CALLED");
		this.resourceId = resourceId;
	}
	public void configure(Dictionary configuration)
   {
		System.out.println(this.getClass().getName() + " CONFIGURE CALKED");
	   if (configuration.get(RESOURCE_ID) != null)
	   {
	   	setResourceId((String) configuration.get(RESOURCE_ID));
	   }
	   		
	   
   }
	public void shutdown()
	{
		System.out.println(this.getClass().getName() + " SHUTDOWN CALLED");
	}
}
