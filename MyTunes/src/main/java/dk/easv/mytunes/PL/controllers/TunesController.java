package dk.easv.mytunes.PL.controllers;

import dk.easv.mytunes.BE.Song;
import dk.easv.mytunes.PL.models.PlaylistModel;
import dk.easv.mytunes.PL.models.SongsModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

public class TunesController implements Initializable {
    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView impBtn;
    @FXML
    private ImageView myMuisc;
    @FXML
    private ImageView playlistMode;

    @FXML
    private ImageView play;
    @FXML
    private ImageView pause;
    @FXML
    private ImageView goBack;
    @FXML
    private ImageView next;
    @FXML
    private ImageView stop;

    @FXML
    private Label musicPlayer;
    @FXML
    private Label myStation;
    @FXML
    private ImageView searchbutton;
    @FXML
    private TextField searchField;

    @FXML
    private ListView<Song> myStationList;

    private MediaPlayerController mediaPlayerController;
    private final SongsModel songsModel = new SongsModel();
    private final PlaylistModel playlistModel = new PlaylistModel(songsModel);

    public TunesController() {
        this.mediaPlayerController = new MediaPlayerController(myStationList);
    }

    public void importSong(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/easv/mytunes/SongImportScene.fxml"));
        Parent root = fxmlLoader.load();

        ImportController importController = fxmlLoader.getController();
        importController.setTunesController(this);

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
           songsModel.loadSongs();

           // Populate the ListView with the loaded songs
           myStationList.setItems(songsModel.getSongs());

           // Add double-click listener to play a song
           myStationList.setOnMouseClicked(event -> {
               if (event.getClickCount() == 2) {
                   Song selectedSong = myStationList.getSelectionModel().getSelectedItem();
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
        //myStationList.setItems(songsModel.getSongs());
        //Add listener to search field
        //searchField.textProperty().addListener((observable, oldValue, newValue) -> filterSongs(newValue));
    }

    private void filterSongs(String query) {
        if(query == null || query.isEmpty()){
            //Rest to the full list if the query is empty
            myStationList.setItems(songsModel.getSongs());
        }else {
            ObservableList<Song> filteredSongs = FXCollections.observableArrayList(
                songsModel.getSongs().stream()
                        .filter(song -> song.getTitle().toLowerCase().contains(query.toLowerCase())|| song.getArtist().toLowerCase().contains(query.toLowerCase()))
                        .toList()

                );
            myStationList.setItems(filteredSongs);
        }
    }
    public void onSearchMode(MouseEvent mouseEvent) {

    }

    public void onPlaylistMode(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/mytunes/PlaylistsScene.fxml"));
        Parent root = loader.load();

        PlaylistsController playlistsController = loader.getController();
        playlistsController.setTunesController(this);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("My Playlists");
        stage.show();
    }

    @FXML
    public void onPlay (MouseEvent event) {
        Song selectedSong = myStationList.getSelectionModel().getSelectedItem();
        mediaPlayerController.play(selectedSong); // Delegate to MediaPlayerController
    }

    @FXML
    public void onPause (MouseEvent event) {
        mediaPlayerController.pause(); // Delegate to MediaPlayerController
    }

    /*@FXML
    public void onStop(MouseEvent event) {
        mediaPlayerController.stop(); // Delegate to MediaPlayerController
    }

     */

    @FXML
    public void onGoBack(MouseEvent mouseEvent) {
        mediaPlayerController.previousSong();
    }

    @FXML
    public void onNext(MouseEvent mouseEvent) {
        mediaPlayerController.nextSong();
    }

    public SongsModel getMySongsModel() {
        return songsModel;
    }
    public PlaylistModel getPlaylistModel() {
        return playlistModel;
    }

    public ListView<Song> getSongsListView() {
        return myStationList;
    }

    public void onMyMusic(MouseEvent mouseEvent) {
    }

    public void onStop(MouseEvent mouseEvent) {
    }
}