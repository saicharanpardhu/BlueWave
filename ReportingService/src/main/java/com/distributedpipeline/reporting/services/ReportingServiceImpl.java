package com.distributedpipeline.reporting.services;

import java.sql.Date;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distributedpipeline.reporting.domain.Report;
import com.distributedpipeline.reporting.exceptions.ReportNotFoundException;
import com.distributedpipeline.reporting.repository.ReportRepository;
import com.distributedpipeline.reporting.utility.LogExecutionTime;
import com.distributedpipeline.reporting.utility.MethodLogger;
import com.jcabi.aspects.Loggable;

//Service implementation

@Service

public class ReportingServiceImpl implements ReportingService{
	
	@Autowired
	private ReportRepository reportRepository;
	  
	
	//<--- Setter-Getter Methods ---> 
	public ReportRepository getReportRepository() {
		return reportRepository;
	}

	public void setReportRepository(ReportRepository reportRepository) {
		this.reportRepository = reportRepository;
	}

	
	//<--- Save Report Method ---> 
		@Override
		public Report saveReport(Report report) {
			// TODO Auto-generated method stub 
//			System.out.println(this.reportRepository.save(report).getId());
			return this.reportRepository.save(report);
	}
	
	//<--- Get Methods ---> 
	@Override
	@LogExecutionTime
	public Report getReportById(Long id) throws ReportNotFoundException {
		// TODO Auto-generated method stub
		if(!this.reportRepository.exists(id)) throw new ReportNotFoundException("No report exists with the supplied date");
		return this.reportRepository.findOne(id);
	}

	@Override
	@LogExecutionTime
	public Iterable<Report> getReportByDate(Date date) throws ReportNotFoundException {
		// TODO Auto-generated method stub
		List<Report> report = new ArrayList<Report>();
		report = this.reportRepository.findByDate(date);
		if(report.size() == 0) throw new ReportNotFoundException("No report exists within the supplied user id.");
		return report;
	}

	@Override
	@LogExecutionTime
	public Iterable<Report> getReportByUser(String userid) throws ReportNotFoundException {
		// TODO Auto-generated method stub
		List<Report> report = new ArrayList<Report>();
		report = this.reportRepository.findByUserid(userid);
		if(report.size() == 0) throw new ReportNotFoundException("No report exists within the supplied user id.");
		return report;
	}

	@Override
	@LogExecutionTime
	public Iterable<Report> getReportByYear(Integer year) throws ReportNotFoundException {
		// TODO Auto-generated method stub
		List<Report> report = new ArrayList<Report>();
		report = this.reportRepository.findByYear(year);
		if(report.size() == 0) throw new ReportNotFoundException("No report exists within the supplied year.");
		return report;
	}
	
	@Override
	@LogExecutionTime
	public Iterable<Report> getAllReports() {
		// TODO Auto-generated method stub 
		return this.reportRepository.findAll();
	}
	 

	
	

	

}
