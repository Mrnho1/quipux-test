package com.quipux.quipuxapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quipux.quipuxapi.model.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

}
