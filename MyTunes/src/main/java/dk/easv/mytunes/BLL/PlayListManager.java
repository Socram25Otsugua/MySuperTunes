package dk.easv.mytunes.BLL;

import dk.easv.mytunes.BE.Playlist;
import dk.easv.mytunes.DAL.IPlaylistDAO;
import dk.easv.mytunes.DAL.PlaylistDAO;

public class PlayListManager {
    private final IPlaylistDAO playlistDAO = new PlaylistDAO();

    public void deletePlaylist(Playlist playlist)
    {
        playlistDAO.deletePlaylist(playlist);
    }

    public void createPlaylist(String name)
    {
        System.out.println("Creating playlist:");
        playlistDAO.createNewPlaylist(new Playlist(1,name));
    }
}
