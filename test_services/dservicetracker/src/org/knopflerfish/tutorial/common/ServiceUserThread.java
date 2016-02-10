package org.knopflerfish.tutorial.common;

import java.util.Date;
import org.knopflerfish.tutorial.dateservice.DateService;

public class ServiceUserThread  extends Thread {
	
	private DateService dateService = null;
	private boolean isRunning = true;
	
	public ServiceUserThread(DateService dateService, String threadName) {
		super(threadName);
		this.dateService = dateService;
	}
	
	public void run() {
		String formattedDate = null;
		
		while(isRunning) {
			Date date = new Date();
			try {
				formattedDate = dateService.getFormattedDate(date);
			} catch(RuntimeException e) {
				System.out.println("RuntimeException occurred during service usage: " + e);
			}
			System.out.println(getName() + ": converted date has value: " + formattedDate);
			try {
				Thread.sleep(3000);
			} catch(InterruptedException e) {
				System.out.println("ServiceUserThread ERROR: " + e);
			}
		}
	}
	
	public void stopThread() {
		System.out.println("Stopping " + this + ".");
		this.isRunning = false;
	}
	
}
