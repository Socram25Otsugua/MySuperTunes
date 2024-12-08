package dk.easv.mytunes.PL.controllers;

import dk.easv.mytunes.BE.Song;
import dk.easv.mytunes.PL.models.SongsModel;
import dk.easv.mytunes.PL.utility.SceneNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MyMusicController {

        @FXML
        private ImageView deleteSong;

        @FXML
        private ImageView goBack;

        @FXML
        private ImageView impBtn;

        @FXML
        private Label musicPlayer;

        @FXML
        private Label mySong;

        @FXML
        private Label myStation;

        @FXML
        private ListView<?> myStationList;

        @FXML
        private ImageView next;

        @FXML
        private AnchorPane pane;

        @FXML
        private ImageView pause;

        @FXML
        private ImageView play;

        @FXML
        private ImageView playlistMode;

        @FXML
        private TextField searchField;

        @FXML
        private ImageView searchbutton;

        @FXML
        private ListView<Song> songsListView;

        private final SongsModel songsModel = new SongsModel();
        private MediaPlayerController mediaPlayerController = new MediaPlayerController(songsListView);

        public void initialize(URL location, ResourceBundle resources) {
                try {
                        // Fetch songs from the database
                        songsModel.loadSongs();

                        // Populate the ListView with the loaded songs
                        songsListView.setItems(songsModel.getSongs());
                        mediaPlayerController = new MediaPlayerController(songsListView);

                        // Add double-click listener to play a song
                        songsListView.setOnMouseClicked(event -> {
                                if (event.getClickCount() == 2) {
                                        Song selectedSong = songsListView.getSelectionModel().getSelectedItem();
                                        if (selectedSong != null) {
                                                mediaPlayerController.play(selectedSong);
                                        } else {
                                                System.out.println("No song selected!");
                                        }
                                }
                        });
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        @FXML
         void importSong(MouseEvent event) {
                try {
                        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                        SceneNavigator.switchScene(currentStage, "/dk/easv/mytunes/SongImportScene.fxml", "Import song");
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        @FXML
        void onPlaylistMode(MouseEvent event) {
                try {
                        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                        SceneNavigator.switchScene(currentStage, "/dk/easv/mytunes/PlaylistsScene.fxml", "Playlists");
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        public void onStationMode(MouseEvent event) {
                try {
                        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                        SceneNavigator.switchScene(currentStage, "/dk/easv/mytunes/MainScene.fxml", "My Tunes");
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        @FXML
        void onSearchMode(MouseEvent event) {

        }

        public void handleAddToPlaylist(ActionEvent event) {
        }

        public void handleEditSong(ActionEvent event) {
        }

        public void handleRemoveSong(ActionEvent event) {
        }
}
