package com.example.ws.api;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ws.model.Greeting;

@RestController
public class GreetingController {
	private static BigInteger nextId;
	private static Map<BigInteger, Greeting> greetingMaps;
	
	public static Greeting save(Greeting greeting){
		if(greetingMaps == null){
			greetingMaps = new HashMap<BigInteger, Greeting>();	
			nextId = BigInteger.ONE;
		}
		greeting.setId(nextId);
		nextId = nextId.add(BigInteger.ONE);
		greetingMaps.put(greeting.getId(), greeting);
		return greeting;
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
		
	}
	
	
	@RequestMapping(value = "/api/greeting", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Greeting>> getGreetings() {
		
		return new ResponseEntity<Collection<Greeting>>(greetingMaps.values(),HttpStatus.OK	);
	}
}
