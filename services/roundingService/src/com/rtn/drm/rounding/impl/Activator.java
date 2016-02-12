package com.rtn.drm.rounding.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


public class Activator implements BundleActivator {

	public static BundleContext bundleContext = null;
	//private simpleGraphicsImpl simpleGraphics = null;
	
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Starting roundingService.");
		Activator.bundleContext = bundleContext;
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Stopping roundingService.");
		Activator.bundleContext = null;
	}

}
