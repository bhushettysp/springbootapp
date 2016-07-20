package com.example.ws.service;

import java.util.Collection;

import com.example.ws.model.Greeting;

public interface GreetingService {
	
	public Collection<Greeting> findAll();

	public Greeting findOne(Long id);

	public Greeting create(Greeting greeting);

	public Greeting update(Greeting greeting);

	public void remove(Long id);

}
