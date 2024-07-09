package com.quipux.quipuxapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quipux.quipuxapi.model.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
	public List<Playlist> findAllByNameContainingIgnoreCase(@Param("name") String name);

	Optional<Playlist> findByName(String name);

	void deleteByName(String name);
}
