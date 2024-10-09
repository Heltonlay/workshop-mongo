package com.danielpsilva.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielpsilva.workshopmongo.domain.User;

@RestController
@RequestMapping("/users")
public class UserResource {
	
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		User u1 = new User("1", "Maria Brown", "maria@gmail.com");
		User u2 = new User("2", "Alex Green", "alex@gmail.com");

		List<User> users = new ArrayList<>();
		users.addAll(Arrays.asList(u1, u2));
		return ResponseEntity.ok().body(users);
	}
}
