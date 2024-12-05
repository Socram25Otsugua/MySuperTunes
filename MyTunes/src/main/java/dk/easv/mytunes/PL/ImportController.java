package dk.easv.mytunes.PL;

import dk.easv.mytunes.PL.controllers.MediaPlayerController;
import dk.easv.mytunes.PL.controllers.TunesController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ImportController
{
    private TunesController tunesController;
    @FXML
    private TextField title;
    @FXML
    private TextField artist;
    @FXML
    private TextField path;
    @FXML
    private ComboBox genre;

    public ImportController()
    {
        //this.tunesController = tunesController;
    }

    public void importFile(ActionEvent event) {
    }

    /*@FXML
    private void importFile()
    {
        if(!title.getText().isEmpty() && !artist.getText().isEmpty() && !path.getText().isEmpty())
        {
            tunesController.addSong(title.getText() + " " + artist.getText() + " " + path.getText());
            Stage stage = (Stage) title.getScene().getWindow();
            stage.close();
        }
    }

     */
}
