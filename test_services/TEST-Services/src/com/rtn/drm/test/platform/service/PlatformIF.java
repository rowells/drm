package com.rtn.drm.test.platform.service;

import java.util.List;

public interface PlatformIF
{
	String PLATFORM_ID = "PLATFORM_ID";
	String getPlatformID();
	List<String> getResources();
}
