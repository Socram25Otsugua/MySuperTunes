package dk.easv.mytunes.DAL;

import dk.easv.mytunes.BE.Playlist;

import java.util.List;

public class PlaylistDAO implements IPlaylistDAO
{
    @Override
    public void createNewPlaylist(Playlist playlist)
    {
        System.out.println("new playlist created: " + playlist);
    }

    @Override
    public List<Playlist> getAllPlaylists()
    {
        return null;
    }

    @Override
    public void updatePlaylist(Playlist playlist)
    {

    }

    @Override
    public void deletePlaylist(Playlist playlist)
    {
        System.out.println(playlist+"playlist deleted from db");
    }
}