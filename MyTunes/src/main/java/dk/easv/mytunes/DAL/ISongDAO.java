package dk.easv.mytunes.DAL;

import dk.easv.mytunes.BE.Song;

import java.util.List;

public interface ISongDAO {
    void addNewSong(Song song);
    List<Song> getAllSongs();
    void removeSong(Song song);
    void updateSong(Song song);
}
