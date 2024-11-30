package dk.easv.mytunes.PL.models;

import dk.easv.mytunes.BE.Song;
import dk.easv.mytunes.BLL.SongManager;
import dk.easv.mytunes.Exceptions.SongExceptions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;

public class MyTunesModel {
    private final SongManager songManager = new SongManager();
    private final ObservableList<Song> songs = FXCollections.observableArrayList();

    public void loadSongs() throws SongExceptions, SQLException {
        songs.setAll(songManager.getAllSongs());
    }

    // Get observable list of songs
    public ObservableList<Song> getSongs() {
        return songs;
    }

    // Add a new song
    public void addSong(String songName) throws SongExceptions {
        Song newSong = songManager.addSong(new Song(-1, songName)); // create song with fake id, get song back with right id
        songs.add(newSong);
    }

    public void removeSong(Song song) throws SongExceptions, SQLException {
        songManager.removeSong(song);
        songs.remove(song);
    }

    public void updateSong(Song song) throws SongExceptions, SQLException {
        songManager.updateSong(song);
        songs.remove(song);
        songs.add(song);
    }
}
}
