package dk.easv.mytunes.DAL;

import dk.easv.mytunes.BE.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongDAO {

    private final DBConnection ds = new DBConnection();

    public List<Song> getAllSongs() throws SQLException {
        List<Song> songs = new ArrayList<>();
        String query = "SELECT * FROM Songs";

        try(Connection connection = ds.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                songs.add(new Song(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("artist"),
                        rs.getString("category"),
                        rs.getString("duration"),
                        rs.getString("file_path")
                ));
            }
        }
        return songs;
        }
        public void addSong(Song song) throws SQLException {
        String query = "INSERT INTO Songs (title, artist, category, file_path, duration) VALUES (?, ?, ?, ?, ?)";

        try(Connection connection = ds.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, song.getTitle());
            statement.setString(2, song.getArtist());
            statement.setString(3, song.getCategory());
            statement.setString(4, song.getFilePath());
            statement.setString(5, song.getDuration());
            statement.executeUpdate();
        }
    }
    public void removeSong(int songId) throws SQLException {
        String query = "DELETE FROM Songs WHERE id = ?";
        Song song = null;

        try(Connection connection = ds.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, songId);
            statement.executeUpdate();
        }
    }
    public Song getSongById(int songId) throws SQLException {
        String query = "SELECT * FROM Songs WHERE id = ?";
        Song song = null;

        try (Connection connection = ds.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, songId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    song = new Song(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("artist"),
                            resultSet.getString("category"),
                            resultSet.getString("file_path"),
                            resultSet.getString("duration")
                    );
                }
            }
        }
        return song;
    }


    public void updateSong(Song song) throws SQLException {
        String query = "UPDATE Songs SET title = ?, artist = ?, category = ?, file_path = ?, duration = ? WHERE id = ?";

        try(Connection connection = ds.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, song.getTitle());
            statement.setString(2, song.getArtist());
            statement.setString(3, song.getCategory());
            statement.setString(4, song.getFilePath());
            statement.setString(5, song.getDuration());
            statement.setInt(6, song.getId());
            statement.executeUpdate();
        }

    }
}
