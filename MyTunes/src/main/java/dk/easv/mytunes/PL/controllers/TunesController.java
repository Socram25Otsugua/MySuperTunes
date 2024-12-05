package dk.easv.mytunes.PL.controllers;

import dk.easv.mytunes.BE.Song;
import dk.easv.mytunes.Exceptions.SongExceptions;
import dk.easv.mytunes.Main;
import dk.easv.mytunes.PL.ImportController;
import dk.easv.mytunes.PL.models.MyTunesModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TunesController implements Initializable {
    @FXML
    private AnchorPane pane;
    @FXML
    private Button impBtn;
    @FXML
    private ListView<Song> songsListView;
    @FXML
    private Label mySong;
    @FXML
    private Label myStation;
    @FXML
    private TextField searchField;

    @FXML
    private ListView<Song> myStationList;

    private final MyTunesModel myTunesModel = new MyTunesModel();
    private ImportController importController;

    public void importSong(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/easv/mytunes/SongImportScene.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Import Song");
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       try {
           // Fetch songs from the database
           myTunesModel.loadSongs();

           // Populate the ListView with the loaded songs
           songsListView.setItems(myTunesModel.getSongs());
       } catch (Exception e) {
           e.printStackTrace();
       }
        myStationList.setItems(myTunesModel.getSongs());
        //Add listener to search field
        searchField.textProperty().addListener((observable, oldValue, newValue) -> filterSongs(newValue));
    }

    private void filterSongs(String query) {
        if(query == null || query.isEmpty()){
            //Rest to the full list if the query is empty
            songsListView.setItems(myTunesModel.getSongs());
        }else {
            ObservableList<Song> filteredSongs = FXCollections.observableArrayList(
                myTunesModel.getSongs().stream()
                        .filter(song -> song.getTitle().toLowerCase().contains(query.toLowerCase())|| song.getArtist().toLowerCase().contains(query.toLowerCase()))
                        .toList()

                );
            songsListView.setItems(filteredSongs);
        }
    }
    public void onSearchMode(MouseEvent mouseEvent) {

    }

    public void onPlaylistMode(MouseEvent mouseEvent) {
        System.out.println("onPlaylistMode clicked");
    }
}