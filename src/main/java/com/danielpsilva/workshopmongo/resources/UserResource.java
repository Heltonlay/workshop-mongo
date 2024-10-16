package com.danielpsilva.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.danielpsilva.workshopmongo.domain.Post;
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

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO userDto) {
		User user = userService.fromDTO(userDto);
		user = userService.insert(user);
		URI path = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(path).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteById(@PathVariable String id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDTO userDto, @PathVariable String id) {
		User user = userService.fromDTO(userDto);
		user.setId(id);
		user = userService.update(user);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
		User user = userService.findById(id);
		List<Post> posts = user.getPosts();
		return ResponseEntity.ok().body(posts);
	}
}
