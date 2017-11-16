package com.distributedpipeline.reporting.config;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ListEndPoints extends AbstractEndpoint<List<Endpoint>> {
	private List<Endpoint> endpoints;

	public ListEndPoints(List<Endpoint> endpoints) {
		super("showendpoints");
		this.endpoints = endpoints;
	}

	@Override
	public List<Endpoint> invoke() {
		return this.endpoints;
	}
}