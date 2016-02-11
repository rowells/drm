package com.rtn.drm.rgbBundle.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.rtn.drm.rgbBundle.IColor;

public class Activator implements BundleActivator {
	
	public static BundleContext bundleContext = null;
	private IColor color = null;
	
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Starting rgbBundle.");
		Activator.bundleContext = bundleContext;
		color = new BlueImpl();
		color.draw();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Stopping rgbBundle.");
		Activator.bundleContext = null;
		color.close();
	}
	
}
