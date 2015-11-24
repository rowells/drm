package com.rtn.drm.bundleStarter.impl;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


// A simple bundle that registers Long Services and 
//  retrieves their service references.
public class Activator implements BundleActivator{
	
	public static BundleContext bundleContext = null;
	private Bundle bundle = null;
	private String bundleToStart = "simple_graphics";
	private String simpleGraphicsPath = "file:C:\\Users\\MICHAEL\\Desktop\\SCHOOL\\FALL 2015\\CSCI 4308\\drm\\services"
			+ "\\simpleGraphicService\\build\\simple_graphics.jar";
	
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Starting bundleStarter.");
		Activator.bundleContext = bundleContext;
//		OLD WAY OF FINDING BUNDLE. DUMB.//		Bundle[] bl = bundleContext.getBundles();
//		for(int i=0; i<bl.length; i++) {
//			if(bl[i].getLocation().contains(bundleToStart)) {
//				bundle = bl[i];
//				break;
//			}
//		}
		
		bundle = bundleContext.installBundle(simpleGraphicsPath);
		if(bundle != null) {
			bundle.start();
		}
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Stopping bundleStarter.");
		if(bundle != null) {
			bundle.stop(); // if we stop simpleGraphics and uninstall it from gui, then stop this bundle, ERROR occurs.
//			TODO: Add service listener to fix error noted above.  Should this bundle should know when simpleGraphics is uninstalled or stopped. 
			bundle.uninstall();
		}
		Activator.bundleContext = null;
	}
}
