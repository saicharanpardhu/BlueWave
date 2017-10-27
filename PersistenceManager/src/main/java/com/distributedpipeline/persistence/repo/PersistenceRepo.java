package com.distributedpipeline.persistence.repo;

import org.springframework.data.repository.CrudRepository;

import com.distributedpipeline.persistence.domain.PersistenceModel;

public interface PersistenceRepo extends CrudRepository<PersistenceModel, Integer> {

}
