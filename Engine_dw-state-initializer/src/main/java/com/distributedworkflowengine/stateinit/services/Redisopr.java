package com.distributedworkflowengine.stateinit.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.distributedworkflowengine.stateinit.domain.Trigger;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface Redisopr{

	public void saveRedis(Trigger trigger) throws JsonProcessingException;
}
