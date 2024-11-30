package dk.easv.mytunes.PL.controllers;

import dk.easv.mytunes.BE.Song;
import dk.easv.mytunes.PL.models.MyTunesModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TunesController implements Initializable {
    @FXML
    private ListView<Song> songsListView;

    @FXML
    private ListView<Song> myStationList;

    private final MyTunesModel myTunesModel = new MyTunesModel();

    public void importSong(ActionEvent event) throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        songsListView.setItems(myTunesModel.getSongs());
        myStationList.setItems(myTunesModel.getSongs());
    }
}