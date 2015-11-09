package com.rtn.drm.test.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.rtn.drm.test.impl.HelloWorld;

public class Activator implements BundleActivator
{
	public BundleContext bundleContext = null;
	private HelloWorld helloWorld = null;

	public void start(BundleContext bc) throws Exception
	{
		System.out.println("SimpleBundle starting...");
		bundleContext = bc;
		helloWorld = new HelloWorld();

	}

	public void stop(BundleContext bc) throws Exception
	{
		bundleContext = null;
		System.out.println("SimpleBundle stopping...");
		helloWorld.shutdown();
	}
}