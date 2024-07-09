package com.quipux.quipuxapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quipux.quipuxapi.model.Playlist;
import com.quipux.quipuxapi.model.Songs;

@Repository
public interface SongsRepository extends JpaRepository<Songs, Long> {

	public List<Songs> findAllByTitleContainingIgnoreCase(@Param("title") String descricao);

	Optional<Songs> findByTitle(String title);

	void deleteByTitle(String title);

}