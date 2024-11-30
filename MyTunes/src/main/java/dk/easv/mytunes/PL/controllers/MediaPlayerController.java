package dk.easv.mytunes.PL.controllers;

import dk.easv.mytunes.Main;
import dk.easv.mytunes.PL.ImportController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MediaPlayerController
{
    private ImportController importController;
    private TunesController tunesController;

    public MediaPlayerController(Stage stage) throws IOException {
    }

    public void addSong(String details)
    {
        tunesController.addSong(details);
    }

    public void importWindow() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SongImportScene.fxml"));

        importController = new ImportController(this);
        fxmlLoader.setController(importController);

        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Import Song");
        stage.show();
    }
}
