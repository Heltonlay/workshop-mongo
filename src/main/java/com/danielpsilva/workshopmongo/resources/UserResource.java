package com.danielpsilva.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielpsilva.workshopmongo.domain.User;
import com.danielpsilva.workshopmongo.dto.UserDTO;
import com.danielpsilva.workshopmongo.services.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = userService.findAll();
		List<UserDTO> users = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(users);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable String id) {
		User user = userService.findById(id);
		return ResponseEntity.ok().body(user);
	}
}
