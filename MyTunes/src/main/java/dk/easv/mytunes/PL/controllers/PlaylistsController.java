package dk.easv.mytunes.PL.controllers;

import dk.easv.mytunes.BE.Playlist;
import dk.easv.mytunes.PL.utility.SceneNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class PlaylistsController {

    @FXML
    private Button createPlaylist;

    @FXML
    private Button deletePlaylist;

    @FXML
    private Button editPlaylist;

    @FXML
    private ImageView impBtn;

    @FXML
    private ImageView myMuisc;

    @FXML
    private ListView<Playlist> myPlaylists;

    @FXML
    private Button removeFromPlaylist;

    @FXML
    private ListView<?> songsOnPlaylist;

    @FXML
    private ImageView stationMode;

    private TunesController tunesController;

    public void setTunesController(TunesController tunesController) {
        this.tunesController = tunesController;
    }

    public void handleCreatePlaylist(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Create Playlist");
        dialog.setHeaderText("Enter the name for the new playlist:");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(playlistName -> {
            try {
                // Add playlist to the database
                tunesController.getPlaylistModel().createPlaylist(new Playlist(0, playlistName));
                System.out.println("Playlist created: " + playlistName);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed to create playlist.");
            }
        });
    }

    public void handleDeletePlaylist(ActionEvent event) {
        // Get the selected playlist
        Playlist selectedPlaylist = getSelectedPlaylist(); // Implement this helper method
        if (selectedPlaylist == null) {
            System.out.println("No playlist selected!");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Playlist");
        alert.setHeaderText("Are you sure you want to delete the playlist: " + selectedPlaylist.getName() + "?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                tunesController.getPlaylistModel().deletePlaylist(selectedPlaylist);
                System.out.println("Playlist deleted: " + selectedPlaylist.getName());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed to delete playlist.");
            }
        }
    }

    private Playlist getSelectedPlaylist() {
        return myPlaylists.getSelectionModel().getSelectedItem();
    }

    public void handleEditPlaylist(ActionEvent event) {
    }

    public void handleRemoveFromPlaylist(ActionEvent event) {
    }

    public void importSong(MouseEvent event) {
        try {
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            SceneNavigator.switchScene(currentStage, "/dk/easv/mytunes/SongImportScene.fxml", "Import Window");
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

    public void onMyMusic(MouseEvent event) {
        try {
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            SceneNavigator.switchScene(currentStage, "/dk/easv/mytunes/MyMusicScene.fxml", "My Music");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
