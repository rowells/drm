package com.rtn.drm.test.date.osgi;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;

import com.rtn.drm.test.date.impl.BasicDateService;
import com.rtn.drm.test.date.service.DateServiceIF;

public class DateServiceActivator implements BundleActivator
{
	public BundleContext bundleContext = null;

	public void start(BundleContext bc) throws Exception
	{
		System.out.println(bc.getBundle().getHeaders().get(Constants.BUNDLE_NAME)
		      + " starting...");
		bundleContext = bc;
		DateServiceIF service = new BasicDateService();
		ServiceRegistration registration = bc.registerService(
		      DateServiceIF.class.getName(), service, null);
		
		System.out.println("Service registered: DateService");
	}

	public void stop(BundleContext bc) throws Exception
	{
		System.out.println(bc.getBundle().getHeaders().get(Constants.BUNDLE_NAME)
		      + " stopping...");
		bundleContext = null;
	}
}