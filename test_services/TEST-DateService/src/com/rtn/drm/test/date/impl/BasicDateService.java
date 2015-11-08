package com.rtn.drm.test.date.impl;

import java.util.Date;

import javax.swing.GroupLayout;

import com.rtn.drm.test.date.service.DateServiceIF;

public class BasicDateService  implements DateServiceIF
{
	
	public BasicDateService()
	{
		super();
		System.out.println(this.getClass().getSimpleName() + " CTOR");
	}

	
	@Override
	public Date getCurrentDate()
	{
		System.out.println(this.getClass().getSimpleName() + " getCurrentDate");
		return new Date();
	}

}
