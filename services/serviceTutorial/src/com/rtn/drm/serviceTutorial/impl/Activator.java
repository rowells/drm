package com.rtn.drm.serviceTutorial.impl;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;


// A simple bundle that registers Long Services and 
//  retrieves their service references.
public class Activator implements BundleActivator{
	
	public static BundleContext bundleContext = null;
	
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Starting serviceTutorial.");
		Activator.bundleContext = bundleContext;
		
		// Register simple object as a service
		Long num = new Long(42);
		Hashtable<String, String> properties = new Hashtable<String, String>();
		properties.put("description", "This is a Long value");
		bundleContext.registerService(Long.class.getName(), num, properties);
		
		// Register a simple object as a service via a ServiceFactory
		ServiceFactory factory = new LongFactory();
		bundleContext.registerService(Long.class.getName(), factory, new Hashtable<String, String>());
		
		// Print out all registered Long Services' longValue
		ServiceReference refs[] = bundleContext.getServiceReferences(Long.class.getName(), null);
		for(int i = 0; i < refs.length; i++) {
			Long theLong = (Long) bundleContext.getService(refs[i]);
			System.out.println("Long Service " + i + " = " + theLong.longValue());
		}
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Stopping serviceTutorial.");
		Activator.bundleContext = null;
	}
	
	// A simple service factory example
	private class LongFactory implements ServiceFactory {
		public Object getService(Bundle bundle, ServiceRegistration reg, Object service) {
			// each bundle gets a Long with its own ID
			return new Long(bundle.getBundleId());
		}
		
		// Needed for ServiceFactory or compiler will complain
		@Override
		public Object getService(Bundle bundle, ServiceRegistration reg) {
			// each bundle gets a Long with its own ID
			return new Long(bundle.getBundleId());
		}
		
		public void ungetService(Bundle bundle, ServiceRegistration reg, Object service) {
			// services automatically released when bundle stops
		}
	}
}
