package dk.easv.mytunes.PL.models;

import dk.easv.mytunes.BE.Playlist;
import dk.easv.mytunes.BE.Song;
import dk.easv.mytunes.BLL.SongManager;
import dk.easv.mytunes.Exceptions.SongExceptions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class MyTunesModel {
    Connection connection;
    private final SongManager songManager = new SongManager();
    private final ObservableList<Song> songs = FXCollections.observableArrayList();

    public void loadSongs() {
        try {
            songs.setAll(songManager.getAllSongs());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get observable list of songs
    public ObservableList<Song> getSongs() {
        return songs;
    }

    // Add a new song
    public void addSong(Song song) throws SongExceptions, SQLException {
        Song newSong = songManager.addSong(song); // create song with fake id, get song back with right id
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
