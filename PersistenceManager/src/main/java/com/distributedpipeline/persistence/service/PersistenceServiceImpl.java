package com.distributedpipeline.persistence.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distributedpipeline.persistence.domain.Workflow;
import com.distributedpipeline.persistence.repo.PersistenceRepo;

@Service
public class PersistenceServiceImpl implements PersistenceService {

	private static final AtomicLong counter = new AtomicLong();
	
	@Autowired
	PersistenceRepo persistenceRepo;
	
	/*----------- getters and setters for repo-------------------*/
	public PersistenceRepo getPersistenceRepo() {
		return persistenceRepo;
	}
    public void setPersistenceRepo(PersistenceRepo persistenceRepo) {
		this.persistenceRepo = persistenceRepo;
	}
    
    /*------------------ get by id method ------------------ */
    @Override
	public Workflow getWorkflow(long id) {
		if(persistenceRepo.findOne((int) id)==null)
		        return null;
		else
		        return persistenceRepo.findOne((int) id);  
}    

    /*-------------- get all method ----------------- */
	@Override
	public List<Workflow> getWorkflow() {
		List<Workflow> list=new ArrayList<>();
		list.addAll((Collection<? extends Workflow>) persistenceRepo.findAll());
		return list;
	}
	
	/*------------------ post method--------------- */
	@Override
	public Workflow addWorkflow(Workflow persistencemodel) {
		persistenceRepo.save(persistencemodel);
		return persistencemodel;
	}

	/*----------- update method ---------------- */
	@Override
	public Workflow updateWorkflow(Workflow persistencemodel) {
		persistenceRepo.save(persistencemodel);
		return persistencemodel;
	}
	
	
    /*----------- delete method -------------*/
	@Override
	public boolean deleteWorkflow(int id) {
		if(persistenceRepo.findOne((int) id)!=null)
		persistenceRepo.delete(id);
		return true;	
	}

}
