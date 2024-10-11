package com.danielpsilva.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danielpsilva.workshopmongo.domain.User;
import com.danielpsilva.workshopmongo.dto.UserDTO;
import com.danielpsilva.workshopmongo.exceptions.ObjectNotFoundException;
import com.danielpsilva.workshopmongo.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(String id) {
		Optional<User> user = repo.findById(id);
		if (user.isEmpty())
			throw new ObjectNotFoundException("Object not found");
		return user.get();
	}

	public User insert(User user) {
		return repo.insert(user);
	}

	public User fromDTO(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
	}
}
