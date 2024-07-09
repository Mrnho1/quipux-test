package com.quipux.quipuxapi.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.quipux.quipuxapi.model.Playlist;
import com.quipux.quipuxapi.model.Songs;
import com.quipux.quipuxapi.repository.SongsRepository;

@RestController
@RequestMapping("/songs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SongsController {

	@Autowired
	private SongsRepository songsRepository;

	@GetMapping
	public ResponseEntity<List<Songs>> getAll() {
		return ResponseEntity.ok(songsRepository.findAll());
	}
	
	@GetMapping("/title/{title}")
	public ResponseEntity<List<Songs>> getByTitle(@PathVariable String title){
		return ResponseEntity.ok(songsRepository.findAllByTitleContainingIgnoreCase(title));
	}
	
	@PostMapping
	public ResponseEntity<Songs> post(@Valid @RequestBody Songs songs){
		return ResponseEntity.status(HttpStatus.CREATED).body(songsRepository.save(songs));
	}
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/title/{title}")
	@Transactional
	public void deleteByTitle(@PathVariable String title) {
	    Optional<Songs> song = songsRepository.findByTitle(title);
	    if (song.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found with title: " + title);
	    }
	    songsRepository.deleteByTitle(title);
	}

}