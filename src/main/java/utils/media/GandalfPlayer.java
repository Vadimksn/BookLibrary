package utils.media;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import utils.ui.UiConstants;

import java.net.URL;

public class GandalfPlayer {

    private static MediaPlayer mediaPlayer;

    public static void play() {
        mediaPlayer = getMediaPlayer();
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
    }

    public static void stop() {
        mediaPlayer.stop();
    }

    private static MediaPlayer getMediaPlayer() {
        URL resource = GandalfPlayer.class.getClassLoader().getResource(UiConstants.Path.GANDALF);
        Media media = new Media(resource.toString());
        return new MediaPlayer(media);
    }

}
