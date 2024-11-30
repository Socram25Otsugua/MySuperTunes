package dk.easv.mytunes.Exceptions;

public class SongExceptions extends Exception {
    public SongExceptions(Exception e) {
        super(e);
    }
    public SongExceptions(String message) {
        super(message);
    }
}
