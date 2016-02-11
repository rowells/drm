package org.knopflerfish.tutorial.dateserviceDeclarative.impl; 

import org.knopflerfish.tutorial.dateservice.DateService; 
import org.knopflerfish.tutorial.common.ServiceUserThread;

public class Component {
  private DateService dateService;
  private ServiceUserThread thread;

  /**
   * Called by the Declarative Service component finds
   * a registered DateService as specified in the component.xml
   */
  protected void setDateService(DateService dateService) {
    log("setDateService.");
    this.dateService = dateService;
    if(thread == null) {
		thread = new ServiceUserThread(this.dateService, "declarative example");
	}
	thread.start();
  }
  
  /**
   * Called by the Declarative Service component notices an
   * unregistered DateService as specified in the component.xml
   */
  protected void unsetDateService(DateService dateService) { 
    log("unsetDateService.");
    this.dateService = null;
    stopUsingService();
  }
  
  private void log(String message) { 
    System.out.println("dateservice Component: " + message); 
  }

  private void stopUsingService() {
	  if(thread != null) {
		  thread.stopThread();
		  try {
			  thread.join();
		  } catch(InterruptedException e) {
			  e.printStackTrace();
		  }
	  }
	  thread = null;
  }
  
} 