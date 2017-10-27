package com.sr.repository;

import java.sql.Date;
import java.time.Year;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sr.domain.Report;

public interface ReportRepository extends CrudRepository<Report, Long>{
	@Query("select report from Report report where YEAR(report.timestamp) = ?1")
	public List<Report> findByYear(Integer year);
	
	@Query("select report from Report report where DATE(report.timestamp) = ?1")
	public List<Report> findByDate(Date date);
	
	public List<Report> findByUserid(String userid);
}
