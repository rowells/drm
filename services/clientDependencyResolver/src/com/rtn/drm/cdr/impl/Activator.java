package com.rtn.drm.cdr.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import com.rtn.drm.cdr.serviceTrackerCustomizer.ColorTrackerCustomizer;
import com.rtn.drm.rgbBundle.IColor;

// TODO Provide the appropriate service tracker 
public class Activator implements BundleActivator {
	
	public static BundleContext bundleContext = null;
	private ServiceTracker colortracker;
	
	public void start(BundleContext bundleContext) throws Exception {
		Activator.bundleContext = bundleContext;
		log("Starting CDR.");
		ServiceTrackerCustomizer customizer = 
				new ColorTrackerCustomizer(Activator.bundleContext);
		colortracker = new ServiceTracker(bundleContext, IColor.class.getName(),customizer);
		colortracker.open();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		log("Stopping CDR.");
		Activator.bundleContext = null;
		colortracker.close();
	}
	
	private void log(String message) {
		System.out.println(Activator.bundleContext.getBundle()
				.getHeaders().get(Constants.BUNDLE_NAME)
                + ": " + message);
	}
	
}
