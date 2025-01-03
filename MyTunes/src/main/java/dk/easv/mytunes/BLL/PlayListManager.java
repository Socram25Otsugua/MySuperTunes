package dk.easv.mytunes.BLL;

import dk.easv.mytunes.BE.Playlist;
import dk.easv.mytunes.BE.Song;
import dk.easv.mytunes.DAL.IPlaylistDAO;
import dk.easv.mytunes.DAL.PlaylistDAO;

import java.sql.SQLException;
import java.util.List;

public class PlayListManager {
    private final PlaylistDAO playlistDAO;

    public PlayListManager() {
        playlistDAO = new PlaylistDAO();
    }
    public void deletePlaylist(Playlist playlist)
    {
        if(playlist!=null){
            playlistDAO.deletePlaylist(playlist);
        }
    }

    public void createPlaylist(String name)
    {
        if (name != null && !name.isEmpty()) {
            Playlist newPlaylist = new Playlist(0, name); // ID is auto-generated by the database
            playlistDAO.createNewPlaylist(newPlaylist);
            System.out.println("Playlist created: " + name);
        }
    }
    public void deleteSongFromPlaylist(Song song, Playlist playlist){
        if(song != null && playlist != null){
            playlistDAO.deleteSongFromPlaylist(song, playlist);
        }
    }

    public List<Playlist> getAllPlaylists() {
        return playlistDAO.getAllPlaylists();
    }

    public void addSongToPlaylist(Song selectedSong, Playlist selectedPlaylist) {
        if (selectedSong != null && selectedPlaylist != null) {
            playlistDAO.addSongToPlaylist(selectedSong, selectedPlaylist);
            System.out.println("Song added to playlist: " + selectedSong.getTitle());
        }
    }
    public List<Song> getSongsForPlaylist(Playlist playlist) {
        return playlistDAO.getSongsForPlaylist(playlist);
    }
}
