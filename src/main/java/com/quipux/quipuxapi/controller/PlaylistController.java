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
import com.quipux.quipuxapi.repository.PlaylistRepository;

@RestController
@RequestMapping("/playlists")
@CrossOrigin(origins = "*", allowedHeaders="*")
public class PlaylistController {
	
	@Autowired
	private PlaylistRepository playlistRepository;

	
	@GetMapping
	public ResponseEntity<List<Playlist>> getAll(){
		return ResponseEntity.ok(playlistRepository.findAll());
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<Playlist>> getByName(@PathVariable String name){
		return ResponseEntity.ok(playlistRepository.findAllByNameContainingIgnoreCase(name));
	}
	@PostMapping
	public ResponseEntity<Playlist> post(@Valid @RequestBody Playlist playlist){
		return ResponseEntity.status(HttpStatus.CREATED).body(playlistRepository.save(playlist));
	}
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/name/{name}")
	@Transactional
	public void deleteByName(@PathVariable String name) {
	    Optional<Playlist> playlist = playlistRepository.findByName(name);
	    if (playlist.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist not found with name: " + name);
	    }
	    playlistRepository.deleteByName(name);
	}
	
}
