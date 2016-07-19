package com.example.ws.api;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	public static boolean delete(BigInteger id){
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
		
	}
	
	
	@RequestMapping(value = "/api/greetings", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Greeting>> getGreetings() {
		
		return new ResponseEntity<Collection<Greeting>>(greetingMaps.values(),HttpStatus.OK	);
	}
	
	@RequestMapping(
            value = "/api/greetings/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> getGreeting(
            @PathVariable("id") BigInteger id) {

        Greeting greeting = greetingMaps.get(id);
        if (greeting == null) {
            return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
    }
	
	
	@RequestMapping(
            value = "/api/greetings",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> createGreeting(
            @RequestBody Greeting greeting) {

        Greeting savedGreeting = save(greeting);

        return new ResponseEntity<Greeting>(savedGreeting, HttpStatus.CREATED);
    }
	
	@RequestMapping(
            value = "/api/greetings/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> updateGreeting(
            @RequestBody Greeting greeting) {

		Greeting updatedGreeting = save(greeting);
        if (updatedGreeting == null) {
            return new ResponseEntity<Greeting>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Greeting>(updatedGreeting, HttpStatus.OK);
    }
	
	@RequestMapping(
            value = "/api/greetings/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> deleteGreeting(
    		 @PathVariable("id") BigInteger id,@RequestBody Greeting greeting) {

		boolean deleted = delete(id);
		
        if (!deleted) {
            return new ResponseEntity<Greeting>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<Greeting>(HttpStatus.NO_CONTENT);
    }
	
}
