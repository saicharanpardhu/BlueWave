package com.distributedpipeline.reporting.repository;

import java.sql.Date;
import java.time.Year;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.distributedpipeline.reporting.domain.Report;

//<!----Crud Repository -->
public interface ReportRepository extends CrudRepository<Report, Long>{
	
	//<!----Custom query to get Reports using Year -->
	@Query("select report from Report report where YEAR(report.timestamp) = ?1")
	public List<Report> findByYear(Integer year);
	
	//<!----Custom query to get reports using dates -->
	@Query("select report from Report report where DATE(report.timestamp) = ?1")
	public List<Report> findByDate(Date date);
	
	public List<Report> findByUserid(String userid);
}
