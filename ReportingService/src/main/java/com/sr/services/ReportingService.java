package com.sr.services;

import java.sql.Date;
import java.time.Year;
import java.util.List;

import com.sr.domain.Report;
import com.sr.exceptions.ReportNotFoundException;

//Interface for all the services

public interface ReportingService {
	public Report getReportById(Long id) throws ReportNotFoundException;
	public Iterable<Report> getReportByDate(Date date) throws ReportNotFoundException;
	public Iterable<Report> getReportByUser(String userID) throws ReportNotFoundException;
	public Iterable<Report> getReportByYear(Integer year) throws ReportNotFoundException;
	public Iterable<Report> getAllReports();
	public Report saveReport(Report report);
}
