package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@PutMapping("/greeting/{id}")
	public Greeting greetingUpdate(@PathVariable("id") long id, @RequestBody Greeting greeting ) {
		return new Greeting(id + greeting.getId(), greeting.getContent());
	}

	/*

	@GetMapping("/greeting")
	public ResponseEntity<List<Greeting>> listAll()
		return new ResponseEntity<>(greetings, HttpStatus.OK);

	@GetMapping("/greeting/{id}")
	public ResponseEntity<Greeting> findGreetingById(@PathVariable("id") long id)
		return new ResponseEntity<Greeting>(foundGreeting, HttpStatus.OK);
		
	@PostMapping("/greeting")
	public ResponseEntity<Greeting> createGreeting(@RequestBody Greeting greeting)
		return new ResponseEntitiy<Greeting>(createdGreeting, HttpStatus.OK);

	@PutMapping("/greeting/{id}")
	public ResponseEntity<Greeting> updateGreeting(@PathVariable("id") long id, @RequestBody Greeting greeting)
		return new ResponseEntity<Greeting>(updatedGreeting, HttpStatus.OK);

	@DeleteMapping("/greeting/{id}")
	public ResponseEntity<?> deleteGreeting(@PathVariable("id") long id)
		return new ResponseEntity<String>("Slettet: " + deletedGreeting, HttpStatus.OK);

	 */
}
