package com.andrewbroughton.lookify.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.andrewbroughton.lookify.models.Song;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {
    @Query(value="select * from song order by song.rating limit 10", nativeQuery=true)
    List<Song> topTen();
    @Query(value="select * from song where song.artist like %:artist", nativeQuery=true)
    List<Song> search(@Param("artist") String artist);
}
