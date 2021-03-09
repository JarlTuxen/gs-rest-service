package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//CrossOrigin bestemmer, hvor kald må komme fra
@CrossOrigin(origins = "*") //kan afgrænses til specifikke ip-adresser
@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greetings")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@PutMapping("/greeting/{id}")
	public Greeting greetingUpdate(@PathVariable("id") long id, @RequestBody Greeting greeting ) {
		return new Greeting(id + greeting.getId(), greeting.getContent());
	}

	@GetMapping("/greeting/{id}")
	public ResponseEntity<Greeting> findGreetingById(@PathVariable("id") long id) {
		Greeting greeting = new Greeting(id, String.format(template, "findByID"));
		HttpHeaders headers = new HttpHeaders();
		headers.add("location", "/greeting/" + greeting.getId());
		return new ResponseEntity<Greeting>(greeting, headers, HttpStatus.CREATED);
		//return ResponseEntity.status(HttpStatus.CREATED).header("location", "/greeting/" + greeting.getId()).body(greeting);
	}

	/*
	//forkerte HttpStatus-koder - se Rest Best Practices & koder i kommentar
	@GetMapping("/greeting")
	public ResponseEntity<List<Greeting>> listAll()
		return new ResponseEntity<>(greetings, HttpStatus.OK); //200 (OK)


	@GetMapping("/greeting/{id}")
	public ResponseEntity<Greeting> findGreetingById(@PathVariable("id") long id)
		return new ResponseEntity<Greeting>(foundGreeting, HttpStatus.OK); //200 (OK), 404 (Not Found)
		
	@PostMapping("/greeting")
	public ResponseEntity<Greeting> createGreeting(@RequestBody Greeting greeting)
		return new ResponseEntitiy<Greeting>(createdGreeting, HttpStatus.OK); //201 (CREATED) + location header

	@PutMapping("/greeting/{id}")
	public ResponseEntity<Greeting> updateGreeting(@PathVariable("id") long id, @RequestBody Greeting greeting)
		return new ResponseEntity<Greeting>(updatedGreeting, HttpStatus.OK); //200 (OK/204 (NO CONTENT, 404 (Not Found)

	@DeleteMapping("/greeting/{id}")
	public ResponseEntity<?> deleteGreeting(@PathVariable("id") long id)
		return new ResponseEntity<String>("Slettet: " + deletedGreeting, HttpStatus.OK); //200 (OK), 404 (Not Found)

	 */
}
