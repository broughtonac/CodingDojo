package com.andrewbroughton.lookify.services;

import org.springframework.stereotype.Service;
import com.andrewbroughton.lookify.models.Song;
import com.andrewbroughton.lookify.repositories.SongRepository;
import java.util.*;


@Service
public class SongService {
	private SongRepository songRepository;
	public SongService(SongRepository songRepository) {
		this.songRepository = songRepository;
	}
	public void create(Song song) {
		songRepository.save(song);
	}
	public Song read(Long id) {
		return songRepository.findOne(id);
	}
	public void destroy(Long id) {
		songRepository.delete(id);
	}
	public List<Song> findAll() {
		return (List<Song>) songRepository.findAll();
	}
	public List<Song> topTen() {
		return songRepository.topTen();
	}
	public List<Song> search(String artist) {
		return (List<Song>) songRepository.search(artist);
	}
}
