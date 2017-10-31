package com.distributedpipeline.persistence.repo;

import org.springframework.data.repository.CrudRepository;

import com.distributedpipeline.persistence.domain.Workflow;

public interface PersistenceRepo extends CrudRepository<Workflow, Integer> {

}
