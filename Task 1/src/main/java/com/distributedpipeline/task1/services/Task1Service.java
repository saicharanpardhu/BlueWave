package com.distributedpipeline.task1.services;

import java.sql.Date;
import java.time.Year;
import java.util.List;

import com.distributedpipeline.task1.exceptions.ReportNotFoundException;

//Interface for all the services

public interface Task1Service {
	public String executeTask1() throws ReportNotFoundException;

}
