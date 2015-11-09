package com.rtn.drm.simpleGraphics.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
	
	public static BundleContext bundleContext = null;
	private simpleGraphicsImpl simpleGraphics = null;
	
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Starting simpleGraphics.");
		Activator.bundleContext = bundleContext;
		simpleGraphics = new simpleGraphicsImpl();
		simpleGraphics.draw();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Stopping simpleGraphics.");
		Activator.bundleContext = null;
		simpleGraphics.close();
		//simpleGraphics = null;
	}
	
}
