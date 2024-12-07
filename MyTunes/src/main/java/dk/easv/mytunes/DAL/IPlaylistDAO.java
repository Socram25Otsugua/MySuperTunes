package dk.easv.mytunes.DAL;

import dk.easv.mytunes.BE.Playlist;
import dk.easv.mytunes.BE.Song;

import java.util.List;

public interface IPlaylistDAO {
    void createNewPlaylist(Playlist playlist);
    List<Playlist> getAllPlaylists();
    void updatePlaylist(Playlist playlist);
    void deletePlaylist(Playlist playlist);
    void addSongToPlaylist(Song song, Playlist playlist);
    void deleteSongFromPlaylist(Song song, Playlist playlist);
}
