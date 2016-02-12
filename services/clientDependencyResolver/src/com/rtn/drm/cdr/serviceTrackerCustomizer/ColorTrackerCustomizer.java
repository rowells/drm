package com.rtn.drm.cdr.serviceTrackerCustomizer;

import java.util.ArrayList;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import com.rtn.drm.rgbBundle.IColor;

public class ColorTrackerCustomizer implements ServiceTrackerCustomizer{

	private final BundleContext bundleContext;
	private ArrayList<IColor> theColors = new ArrayList<IColor>();
	
	public ColorTrackerCustomizer(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}
	
	@Override
	public Object addingService(ServiceReference serviceReference) {
		log("Adding Color Service");
		IColor colorService = (IColor) bundleContext.getService(serviceReference);
		if(theColors.isEmpty()) {
			theColors.add(colorService);
		} else {
			for(IColor c : theColors) {
				if(c != colorService) {
					theColors.add(colorService);
				}
			}
		}
		colorService.draw();
		return colorService;
	}

	@Override
	public void modifiedService(ServiceReference serviceReference, Object service) {
		// TODO update/reinitialize some service
	}

	@Override
	public void removedService(ServiceReference serviceReference, Object service) {
		log("Removing Color Service");
		IColor colorService = (IColor) bundleContext.getService(serviceReference);
		if(!theColors.isEmpty()) {
			for(IColor c : theColors) {
				if(c == colorService) {
					theColors.remove(c);
				}
			}
		}
		bundleContext.ungetService(serviceReference);
	}
	
	private void log(String message) {
		System.out.println(bundleContext.getBundle().
				getHeaders().get(Constants.BUNDLE_NAME)
				+ "-- tracker: " + message);
	}

}
