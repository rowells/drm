package com.rtn.drm.test.resource.osgi;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedServiceFactory;

import com.rtn.drm.test.resource.impl.Resource;
import com.rtn.drm.test.resource.service.ResourceIF;

public class ResourceActivator implements BundleActivator, ManagedServiceFactory
{
	private BundleContext bundleContext = null;
	private ServiceRegistration serviceRegistration = null;
	private Map<String, Resource> resources = new HashMap<String, Resource>();
	private Map<String, ServiceRegistration> resourceRegistrations = new HashMap<String, ServiceRegistration>();
	@Override
	public void start(BundleContext bundleContext) throws Exception
	{
		System.out.println(this.getClass().getName() + " START called");
		this.bundleContext = bundleContext;
		Dictionary<String, Object> properties = new Hashtable<String, Object>();
		properties.put(Constants.SERVICE_PID, "TEST-Resource");
		System.out.println(this.getClass().getName() + " Registring my pid");
		serviceRegistration = bundleContext.registerService(
		      ManagedServiceFactory.class.getName(), this, properties);

	}

	@Override
	public void stop(BundleContext arg0) throws Exception
	{
		System.out.println(this.getClass().getName() + " STOP called");
		this.bundleContext = null;

	}

	@Override
	public void deleted(String pid)
	{
		System.out.println(this.getClass().getName() + " DELETED called ");
		stopResource(pid);
	}

	@Override
	public String getName()
	{
		return "Resource ManagedServiceFactory";
	}

	@Override
	public void updated(String pid, Dictionary configuration)
	      throws ConfigurationException
	{
		System.out.println(this.getClass().getName() + " UPDATED");
		if (!resources.containsKey(pid))
		{
		startResource(pid, configuration);
		}
		else
		{
			throw new ConfigurationException(ResourceIF.RESOURCE_ID, "Update to existing service not allowed");
		}
	}
	private void startResource(String pid, Dictionary configuration)
	{
		System.out.println(this.getClass().getName() + " Starting resource");
		Resource resource = new Resource();
		resources.put(pid, resource);
		resource.configure(configuration);
		Dictionary<String, Object> properties = new Hashtable<String, Object>();
		properties.put(ResourceIF.RESOURCE_ID, resource.getResourceId());
		
		ServiceRegistration reg= bundleContext.registerService(ResourceIF.class.getName(), resource, properties);
		resourceRegistrations.put(pid, reg);
	}
	private void stopResource(String pid)
	{
		System.out.println(this.getClass().getName() + " Stopping resource");
		Resource resource = resources.remove(pid);
		if (resource != null)
		{
			resource.shutdown();
			resourceRegistrations.remove(pid).unregister();
			
		}
	}
}
