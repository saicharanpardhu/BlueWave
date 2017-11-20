package com.sr.report.repository;

import java.security.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.sr.report.model.ReportModel;

public interface ReportModelCRUDRepository extends CassandraRepository<ReportModel> {
	
	
	public List<ReportModel> findByJobId(String jobId);
    public ReportModel save(ReportModel reportModel);
   
    
    

}