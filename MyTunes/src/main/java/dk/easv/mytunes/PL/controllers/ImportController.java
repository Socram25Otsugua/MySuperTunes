package dk.easv.mytunes.PL.controllers;

import dk.easv.mytunes.BE.Song;
import dk.easv.mytunes.PL.utility.SceneNavigator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ImportController {

    private TunesController tunesController;

    public void setTunesController(TunesController tunesController) {
        this.tunesController = tunesController;
    }

    @FXML
    private Button browseButton;
    @FXML
    private TextField title;
    @FXML
    private TextField artist;
    @FXML
    private TextField path;
    @FXML
    private ComboBox<String> genre;

    public void initialize() {
        genre.getItems().addAll("Pop", "Rock", "Jazz", "Classical", "Hip-Hop", "Electronic", "Country", "Reggae");
    }


    @FXML
    public void handleBrowse() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Song File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Audio Files", "*.mp3", "*.wav", "*.flac")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            path.setText(selectedFile.getAbsolutePath());
        }
    }

    @FXML
    private void importFile() {
        String songTitle = title.getText();
        String songArtist = artist.getText();
        String songGenre = genre.getValue();
        String songPath = path.getText();

        if (songTitle.isEmpty() || songArtist.isEmpty() || songGenre == null || songPath.isEmpty()) {
            System.out.println("All fields must be filled out");
            return;
        }
        try {
            //Create a new song object
            Song newSong = new Song(0, songTitle, songArtist, songGenre, songPath, "");
            tunesController.getMySongsModel().addSong(newSong);
            tunesController.getSongsListView().getItems().add(newSong);

            // Clear the form
            title.clear();
            artist.clear();
            genre.setValue(null);
            path.clear();

            System.out.println("Song imported successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to import the song.");
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
}
