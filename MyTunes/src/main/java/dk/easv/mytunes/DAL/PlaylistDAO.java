package dk.easv.mytunes.DAL;

import dk.easv.mytunes.BE.Playlist;
import dk.easv.mytunes.BE.Song;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class PlaylistDAO implements IPlaylistDAO {

    private final DBConnection dbConnection;

    public PlaylistDAO() {
        dbConnection = new DBConnection();
    }

    @Override
    public void createNewPlaylist(Playlist playlist) {
        String sql = "INSERT INTO Playlists (name) VALUES (?)";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, playlist.getName());
            pstmt.executeUpdate();

            // Retrieve generated ID
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    playlist.setId(generatedKeys.getInt(1));
                }
            }

            System.out.println("New playlist created: " + playlist.getName());
        } catch (SQLException e) {
            System.out.println("Failed to create playlist: " + e.getMessage());
        }
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        String sql = "SELECT * FROM Playlists";
        List<Playlist> playlists = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                playlists.add(new Playlist(id, name));
            }

        } catch (SQLException e) {
            System.out.println("Failed to fetch playlists: " + e.getMessage());
        }
        return playlists;
    }

    @Override
    public void updatePlaylist(Playlist playlist) {
        String sql = "UPDATE Playlists SET name = ? WHERE id = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, playlist.getName());
            pstmt.setInt(2, playlist.getId());
            pstmt.executeUpdate();

            System.out.println("Playlist updated: " + playlist.getName());
        } catch (SQLException e) {
            System.out.println("Failed to update playlist: " + e.getMessage());
        }
    }

    @Override
    public void deletePlaylist(Playlist playlist) {
        String sql = "DELETE FROM Playlists WHERE id = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, playlist.getId());
            pstmt.executeUpdate();

            System.out.println("Playlist deleted: " + playlist.getName());
        } catch (SQLException e) {
            System.out.println("Failed to delete playlist: " + e.getMessage());
        }
    }
    public void addSongToPlaylist(Song song, Playlist playlist) {
        String sql = "INSERT INTO PlaylistSongs (playlist_id, song_id) VALUES (?, ?)";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, playlist.getId());
            pstmt.setInt(2, song.getId());
            pstmt.executeUpdate();

            System.out.println("Song added to playlist in the database: " + song.getTitle());
        } catch (SQLException e) {
            System.out.println("Failed to add song to playlist: " + e.getMessage());
        }
    }

    public void deleteSongFromPlaylist(Song song, Playlist playlist) {
        String sql = "DELETE FROM PlaylistSongs WHERE playlist_id = ? AND song_id = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, playlist.getId());
            pstmt.setInt(2, song.getId());
            pstmt.executeUpdate();

            System.out.println("Song removed from playlist in the database: " + song.getTitle());
        } catch (SQLException e) {
            System.out.println("Failed to remove song from playlist: " + e.getMessage());
        }
    }

    public List<Song> getSongsForPlaylist(Playlist playlist) {
        List<Song> songs = new ArrayList<>();
        String sql = "SELECT s.id, s.title, s.artist, s.category, s.file_path, s.duration " +
                "FROM Songs s " +
                "INNER JOIN PlaylistSongs ps ON s.id = ps.song_id " +
                "WHERE ps.playlist_id = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, playlist.getId());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String artist = rs.getString("artist");
                    String category = rs.getString("category");
                    String filePath = rs.getString("file_path");
                    String duration = rs.getString("duration");

                    Song song = new Song(id, title, artist, category, filePath, duration);
                    songs.add(song);
                }
            }

        } catch (SQLException e) {
            System.out.println("Failed to fetch songs for playlist: " + e.getMessage());
        }

        return songs;
    }
}