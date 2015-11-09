package com.rtn.drm.servicedaemon.impl;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

public class ServiceDaemon
{
	private ConfigurationAdmin configurationAdmin;
	private String serviceFile = null;
	public ServiceDaemon()
	{
		super();
		System.out.println(this.getClass().getSimpleName()
		      + " CTOR");
	}
	public void setConfigurationAdmin(ConfigurationAdmin configurationAdmin)
	{
		System.out.println(this.getClass().getSimpleName()
		      + " Setting Configuration Admin");
		this.configurationAdmin = configurationAdmin;
		try
      {
	      Configuration config = configurationAdmin.createFactoryConfiguration("TEST-Resource", null);
	      Dictionary cfg = new Hashtable<String, Object>();
	      cfg.put("RESOURCE_ID", "cougars");
	      config.update(cfg);
      }
      catch (IOException e)
      {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
      }
		
	}
	
	public void shutdown()
	{
		setConfigurationAdmin(null);
	}
	public void setServiceFile(String serviceFile)
	{
		System.out.println(this.getClass().getSimpleName()
		      + " setServiceFile " + serviceFile);
		this.serviceFile = serviceFile;
	}
}
