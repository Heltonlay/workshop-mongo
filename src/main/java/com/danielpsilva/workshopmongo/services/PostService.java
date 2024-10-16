package com.danielpsilva.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danielpsilva.workshopmongo.domain.Post;
import com.danielpsilva.workshopmongo.repositories.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public List<Post> findByTitle(String text) {
		List<Post> post = repo.findByTitleContainingIgnoreCase(text);
		return post;
	}
}
