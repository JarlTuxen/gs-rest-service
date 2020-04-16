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

	@GetMapping
	public ResponseEntity<List<Greeting>> listAll()

	@GetMapping("/{id}")
	public ResponseEntity<Greeting> findGreetingById(@PathVariable("id") long id)

	@PostMapping
	public ResponseEntity<Greeting> createGreeting(@RequestBody Greeting greeting)

	@PutMapping("/{id}")
	public ResponseEntity<Greeting> updateGreeting(@PathVariable("id") long id, @RequestBody Greeting greeting)

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteGreeting(@PathVariable("id") long id)

	 */
}
