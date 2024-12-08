package dk.easv.mytunes.PL.controllers;

import dk.easv.mytunes.BE.Song;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;


public class MediaPlayerController
{
    private MediaPlayer mediaPlayer;
    private ListView<Song> songsListView;

    public MediaPlayerController(ListView<Song> songsListView) {
        this.songsListView = songsListView;
    }

    //play th selected song
    public void play(Song song){
        if(song != null){
            if(mediaPlayer != null){
                mediaPlayer.stop(); //to stop any music that is playing
            }
            try {
                System.out.println("Trying to play: " + song.getFilePath() + song.getDuration());
                Media media = new Media(new File(song.getFilePath()).toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
            } catch (Exception e){
                e.printStackTrace();
                System.out.println("Error playing the song:" + song.getFilePath());
            }
        } else {
            System.out.println("Select a song!");
        }
    }
    // Pause the currently playing song
    public void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    // Stop the currently playing song
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    // Move to the previous song
    public void previousSong() {
        int currentIndex = songsListView.getSelectionModel().getSelectedIndex();
        if (currentIndex > 0) { // Ensure there is a previous song
            int previousIndex = currentIndex - 1;
            songsListView.getSelectionModel().select(previousIndex);
            play(songsListView.getSelectionModel().getSelectedItem());
        } else {
            System.out.println("Already at the first song!");
        }
    }

    // Move to the next song
    public void nextSong() {
        int currentIndex = songsListView.getSelectionModel().getSelectedIndex();
        if (currentIndex < songsListView.getItems().size() - 1) { // Ensure there is a next song
            int nextIndex = currentIndex + 1;
            songsListView.getSelectionModel().select(nextIndex);
            play(songsListView.getSelectionModel().getSelectedItem());
        } else {
            System.out.println("Already at the last song!");
        }
    }
}
