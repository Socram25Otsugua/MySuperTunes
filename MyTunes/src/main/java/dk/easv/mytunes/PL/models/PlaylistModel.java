package dk.easv.mytunes.PL.models;

import dk.easv.mytunes.BE.Playlist;
import dk.easv.mytunes.BE.Song;
import dk.easv.mytunes.BLL.PlayListManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaylistModel {
    private final PlayListManager playlistManager;
    private final SongsModel songsModel;

    private final Map<Playlist, ObservableList<Song>> playlistSongs;
    private final ObservableList<Playlist> playlists;

    public PlaylistModel(SongsModel songsModel){
        this.songsModel = songsModel;
        playlistManager = new PlayListManager();
        playlistSongs = new HashMap<>();
        playlists = FXCollections.observableArrayList();

        populatePlaylists();
    }

    private void populatePlaylists() {
        // Fetch all playlists through the PlaylistManager
        List<Playlist> fetchedPlaylists = playlistManager.getAllPlaylists();
        playlists.setAll(fetchedPlaylists);  // Update observable list for UI

        for (Playlist playlist : fetchedPlaylists) {
            // Fetch songs for the current playlist
            List<Song> songsOnPlaylist = playlistManager.getSongsForPlaylist(playlist);
            // Add songs to the Playlist object
            playlist.addAllSongs(songsOnPlaylist);

            // Create an ObservableList for UI
            ObservableList<Song> observableList = FXCollections.observableArrayList(songsOnPlaylist);
            playlistSongs.put(playlist, observableList);
        }
    }
        public ObservableList<Playlist> getPlaylists() {
            return playlists;
        }

        public ObservableList<Song> getSongsForPlaylist(Playlist playlist){
            return playlistSongs.get(playlist);
        }

    public void createPlaylist( Playlist newPlaylist ) {
        playlists.add(newPlaylist);
        playlistManager.createPlaylist(newPlaylist.getName());
        ObservableList<Song> observableList = FXCollections.observableArrayList();
        playlistSongs.put(newPlaylist, observableList);
    }

    public void deletePlaylist(Playlist playlistToRemove) {
        if (playlistToRemove != null) {
            playlists.remove(playlistToRemove);
            playlistManager.deletePlaylist(playlistToRemove);
            playlistSongs.remove(playlistToRemove);
        }
    }
    public void removeSongFromPlaylist(Song song, Playlist playlist){
        if (song != null && playlist != null) {
            playlist.removeSong(song);
            ObservableList<Song> songs = playlistSongs.get(playlist);
            if (songs != null) {
                songs.remove(song);
            }
            playlistManager.deleteSongFromPlaylist(song, playlist);
        }
    }

    public void addSongToPlaylist(Song selectedSong, Playlist selectedPlaylist) {
        if (selectedSong != null && selectedPlaylist != null) {
            selectedPlaylist.addSong(selectedSong);
            ObservableList<Song> songs = playlistSongs.get(selectedPlaylist);
            if (songs != null && !songs.contains(selectedSong)) {
                songs.add(selectedSong);
                playlistManager.addSongToPlaylist(selectedSong, selectedPlaylist);
            }
        }
    }
}


