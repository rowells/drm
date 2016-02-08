package com.rtn.drm.cdr.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.util.tracker.ServiceTracker;

import com.rtn.drm.cdr.serviceTrackerCustomizer.SomeServiceTrackerCustomizer;

// TODO Provide the appropriate service tracker 
public class Activator implements BundleActivator {
	
	public static BundleContext bundleContext = null;
	private ServiceTracker someServiceTracker;
	
	public void start(BundleContext bundleContext) throws Exception {
		Activator.bundleContext = bundleContext;
		log("Starting CDR.");
		// TODO Start tracking services, e.g.:
		//SomeServiceTrackerCustomizer tracker = 
		//		new SomeServiceTrackerCustomizer(Activator.bundleContext);
		//someServiceTracker = new ServiceTracker(bundleContext, filter, tracker);
		//someServiceTracker.open();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		log("Stopping CDR.");
		Activator.bundleContext = null;
		// TODO Stop tracking services, e.g.:
		//someServicetracker.close();
	}
	
	private void log(String message) {
		System.out.println(Activator.bundleContext.getBundle()
				.getHeaders().get(Constants.BUNDLE_NAME)
                + ": " + message);
	}
	
}
