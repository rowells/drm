package com.rtn.drm.test.dateuser.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import com.rtn.drm.test.date.service.DateServiceIF;
import com.rtn.drm.test.dateuser.impl.DateServiceUser;

public class DateServiceUserActivator implements BundleActivator
{

	private BundleContext bundleContext = null;
	private DateServiceUser dateServiceUser;
	private ServiceTracker tracker = null;
	private ServiceListener serviceListener = null;

	@Override
	public void start(BundleContext bundleContext) throws Exception
	{
		System.out.println(this.getClass().getSimpleName() + " Activator Start");
		this.bundleContext = bundleContext;
		Filter filter = bundleContext.createFilter("(objectClass="
		      + DateServiceIF.class.getName() + ")");
		System.out.println(this.getClass().getSimpleName() + " Starting Tracker");
		tracker = new ServiceTracker(bundleContext, filter,
		      new ServiceTrackerCustomizer()
		      {

			      @Override
			      public Object addingService(ServiceReference serviceReference)
			      {
				      System.out.println(this.getClass().getSimpleName()
				            + " ServiceTrackerCustomizer Adding ServiFce Called");
				      DateServiceIF dateService = null;
				      if (serviceReference != null)
				      {
					      if (bundleContext.getService(serviceReference) instanceof DateServiceIF)
					      {

						      dateService = (DateServiceIF) bundleContext
						            .getService(serviceReference);
						      if (dateServiceUser != null)
						      {
							      System.out.println(this.getClass().getSimpleName()
							            + " date service already started. ");
						      } else if (dateService != null)
						      {
							      System.out.println(this.getClass().getSimpleName()
							            + " Activator Found Date Service");
							      dateServiceUser = new DateServiceUser();
							      dateServiceUser.setDateService(dateService);
							      dateServiceUser.init();
						      } else
						      {
							      System.out.println("Date service is null");
						      }
					      } else
					      {
						      System.out.println("Wrong service type "
						            + bundleContext.getService(serviceReference)
						                  .getClass().getName());
					      }
				      }
				      return dateService;
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
				      dateServiceUser.setDateService(null);
			      }

		      });
		tracker.open();
		System.out.println(this.getClass().getSimpleName()
		      + " Activator Start Complete 1");
	}

	@Override
	public void stop(BundleContext arg0) throws Exception
	{
		System.out.println(this.getClass().getSimpleName() + " Activator Stop");

		dateServiceUser.shutdown();

		tracker.close();
		bundleContext.removeServiceListener(serviceListener);

	}
}
