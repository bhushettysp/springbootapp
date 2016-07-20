package com.example.ws.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ws.model.Greeting;
import com.example.ws.repository.GreetingRepository;

@Service
public class GreetingServiceBean implements GreetingService {
	
	/*
	private static BigInteger nextId;
	private static Map<BigInteger, Greeting> greetingMaps;
	
	private static Greeting save(Greeting greeting){
		if(greetingMaps == null){
			greetingMaps = new HashMap<BigInteger, Greeting>();	
			nextId = BigInteger.ONE;
		}
		//update code
		if(greeting.getId()!=null){
			if(greetingMaps.get(greeting.getId())== null){
				return null;
			}
			greetingMaps.remove(greeting.getId());
			greetingMaps.put(greeting.getId(), greeting);
			return greeting;
		}
		// create code
		greeting.setId(nextId);
		nextId = nextId.add(BigInteger.ONE);
		greetingMaps.put(greeting.getId(), greeting);
		return greeting;
	}
	
	private static boolean delete(BigInteger id){
		Greeting deletedGreeting = greetingMaps.remove(id);
		if(deletedGreeting == null)
			return false;
		return true;
	}
	
	static{
		Greeting g1 = new Greeting();
		g1.setText("This is my Spring boot first resource");
		save(g1);
		
		Greeting g2 = new Greeting();
		g2.setText("This is testing my Spring boot application");
		save(g2);
		
		Greeting g3 = new Greeting();
		g3.setText("This is testing second my Spring boot application");
		save(g3);
		
		Greeting g4 = new Greeting();
		g4.setText("This is testing third my Spring boot application");
		save(g4);
		
		Greeting g5 = new Greeting();
		g5.setText("This is testing fourth my Spring boot application");
		save(g5);
		
	}*/
	
	@Autowired
	public GreetingRepository greetingRepository;
	
	@Override
	public Collection<Greeting> findAll() {
		Collection<Greeting> greetings = greetingRepository.findAll();
		return greetings;
	}

	@Override
	public Greeting findOne(Long id) {
		Greeting greeting = greetingRepository.findOne(id);
		return greeting;
	}

	@Override
	public Greeting create(Greeting greeting) {
		if(greeting.getId() != null)
			return null;
		Greeting savedGreeting = greetingRepository.save(greeting);
		return savedGreeting;
	}

	@Override
	public Greeting update(Greeting greeting) {
		if(greeting.getId() == null){
			return null;
		}
		Greeting updatedGreeting = greetingRepository.save(greeting);
		return updatedGreeting;
	}

	@Override
	public void remove(Long id) {

		greetingRepository.delete(id);
	
	}

}
