package com.rtn.drm.cdr.serviceTrackerCustomizer;

import org.osgi.framework.ServiceReference;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

// TODO Implement this service tracker customizer for one or more specific services.
public class SomeServiceTrackerCustomizer implements ServiceTrackerCustomizer{

	private final BundleContext bundleContext;
	
	public SomeServiceTrackerCustomizer(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}
	
	@Override
	public Object addingService(ServiceReference serviceReference) {
		// TODO add some service
		log("Adding service");
		return null;
	}

	@Override
	public void modifiedService(ServiceReference serviceReference, Object service) {
		// TODO update/reinitialize some service
	}

	@Override
	public void removedService(ServiceReference serviceReference, Object service) {
		// TODO remove some service
		log("Removing service");
		bundleContext.ungetService(serviceReference);
	}
	
	private void log(String message) {
		System.out.println(bundleContext.getBundle().
				getHeaders().get(Constants.BUNDLE_NAME)
				+ "-- tracker: " + message);
	}

}
