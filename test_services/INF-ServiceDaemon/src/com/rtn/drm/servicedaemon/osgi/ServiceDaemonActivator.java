package com.rtn.drm.servicedaemon.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import com.rtn.drm.servicedaemon.impl.ServiceDaemon;

public class ServiceDaemonActivator implements BundleActivator
{
	private static final String SERVICE_FILE = "SERVICE_FILE";
	private BundleContext bundleContext = null;
	private ServiceDaemon serviceDaemon = null;
	private ServiceTracker caTracker = null;

	@Override
	public void start(BundleContext bundleContext) throws Exception
	{
		System.out.println(this.getClass().getSimpleName() + " Activator Start");

		this.bundleContext = bundleContext;
		Filter filter = bundleContext.createFilter("(objectClass="
		      + ConfigurationAdmin.class.getName() + ")");
		System.out.println(this.getClass().getSimpleName() + " Starting CA Tracker");
		caTracker = new ServiceTracker(bundleContext, filter, new ServiceTrackerCustomizer()
		{

			@Override
			public Object addingService(ServiceReference serviceReference)
			{
				System.out.println(this.getClass().getSimpleName()
				      + " ServiceTrackerCustomizer Adding Service Called");
				ConfigurationAdmin configurationAdmin = null;
				if (serviceReference != null)
				{
					if (bundleContext.getService(serviceReference) instanceof ConfigurationAdmin)
					{

						configurationAdmin = (ConfigurationAdmin) bundleContext
						      .getService(serviceReference);
						if (serviceDaemon != null)
						{
							System.out.println(this.getClass().getSimpleName()
							      + " ServiceDaemon already started. ");
						}
						else if (configurationAdmin != null)
						{
							System.out.println(this.getClass().getSimpleName()
							      + " Activator Found ConfigurationAdmin");
							System.out.println(this.getClass().getSimpleName()
							      + " Creating Service Daemon");
							serviceDaemon = new ServiceDaemon();
							System.out.println(this.getClass().getSimpleName()
							      + " Setting ConfigurationAdmon");
							serviceDaemon.setConfigurationAdmin(configurationAdmin);
							serviceDaemon.setServiceFile(bundleContext.getProperty(SERVICE_FILE));

						}
						else
						{
							System.out.println("ConfigurationAdmin service is null");
						}
					}
					else
					{
						System.out.println("Wrong service type "
						      + bundleContext.getService(serviceReference).getClass().getName());
					}
				}
				return configurationAdmin;
			}

			@Override
			public void modifiedService(ServiceReference arg0, Object arg1)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void removedService(ServiceReference arg0, Object arg1)
			{
				System.out.println(this.getClass().getSimpleName()
				      + " ServiceTrackerCustomizer removeService called");
				serviceDaemon.setConfigurationAdmin(null);
			}

		});
		caTracker.open();
		System.out.println(this.getClass().getSimpleName() + " Activator Start Complete");
	}

	@Override
	public void stop(BundleContext arg0) throws Exception
	{
		System.out.println(this.getClass().getSimpleName() + " Activator Stop");

		serviceDaemon.shutdown();

		caTracker.close();

	}
}
