package com.distributedpipeline.userpersistence.service;

import org.eclipse.core.internal.resources.Project;

public interface ProjectService {
	
	public Project getallproject();
	public Project getprojectbyname();
	public Project deleteproject();

}
