package dk.easv.mytunes.DAL;

import dk.easv.mytunes.BE.Playlist;

import java.util.List;

public interface IPlaylistDAO {
    void createNewPlaylist(Playlist playlist);
    List<Playlist> getAllPlaylists();
    void updatePlaylist(Playlist playlist);
    void deletePlaylist(Playlist playlist);
}
