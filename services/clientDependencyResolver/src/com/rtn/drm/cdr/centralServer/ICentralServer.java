package com.rtn.drm.cdr.centralServer;

import org.osgi.framework.BundleContext;
import java.io.File;
import java.io.DataInputStream;
//import java.net.Socket;

// TODO Nail down the details of central-server communication.
public interface ICentralServer {
	public void setBundleContext(BundleContext bundleContext);
	public BundleContext getBundleContext();
	
	public void installBundle(DataInputStream dataInputStream);
	public void uninstallBundle(String bundleSymbolicName);
	public void sendBundle(File jarFile);
	public File requestBundle(String bundleSymbolicName);
	
	// Other pertinent methods
}
