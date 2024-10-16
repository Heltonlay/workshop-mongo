package com.danielpsilva.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danielpsilva.workshopmongo.domain.Post;
import com.danielpsilva.workshopmongo.resources.utils.URL;
import com.danielpsilva.workshopmongo.services.PostService;

@RestController
@RequestMapping("/posts")
public class PostResource {

	@Autowired
	private PostService postService;

	@GetMapping("/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text") String text) {
		List<Post> posts = postService.findByTitle(URL.decodedURL(text));
		return ResponseEntity.ok().body(posts);
	}
}
