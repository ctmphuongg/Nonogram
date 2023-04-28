/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023
 * Instructor: Prof. Brian King
 *
 * Name: MINHPHUONG CAO
 * Section: Section 01
 * Date: 4/22/23
 * Time: 9:17 PM
 *
 * Project: csci205_final_project
 * Package: org.team3
 * Class: MusicPlayer*
 * Description:
 *
 * *****************************************/

package org.team3;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URISyntaxException;

/** A class for music background */
public class MusicPlayer {

    /** Media stores the music */
    private Media background;

    /** Media player to control the music background */
    private static MediaPlayer mediaPlayer;

    /**
     * Play music
     * @throws URISyntaxException
     */
    public void play() throws URISyntaxException {
        background = new Media(getClass().getResource("/music/Fluffing-a-Duck.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(background);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    /**
     * Play this music when player chooses incorrect square
     * @throws URISyntaxException
     */
    public void playWrong() throws URISyntaxException {
        Media wrongEffect = new Media(getClass().getResource("/music/error.mp3").toURI().toString());
        MediaPlayer playWrong = new MediaPlayer(wrongEffect);
        double effectVolume = mediaPlayer.getVolume() + 1;
        playWrong.setVolume(effectVolume);
        playWrong.play();
    }

    /**
     * Set the volume of the music
     * @param volume - volume ranging from 0 to 1
     */
    public static void setVolume(double volume) {
        mediaPlayer.setVolume(volume);
    }
}
    