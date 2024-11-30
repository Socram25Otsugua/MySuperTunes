package dk.easv.mytunes;

import dk.easv.mytunes.PL.ImportController;
import dk.easv.mytunes.PL.controllers.MediaPlayerController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private ObservableList<String> songList;
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainScene.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("My Tunes");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}