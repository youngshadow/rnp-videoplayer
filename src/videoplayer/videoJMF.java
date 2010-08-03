/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videoplayer;

import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.Time;
import javax.swing.JProgressBar;

/**
 *
 * @author bisaggio
 */
public class videoJMF extends javax.swing.JPanel {

    private Player mediaPlayer;
    private Component video;
    private TimerTask task;
    private int frame = 0;
    private Timer tempo;
    private int speed = 1000;

    public videoJMF(URL url, Dimension d) {
        Manager.setHint(Manager.LIGHTWEIGHT_RENDERER, true);
        try {
            mediaPlayer = Manager.createRealizedPlayer(url);
            video = mediaPlayer.getVisualComponent();
            video.setSize(d);
            add(video);

        } catch (IOException ex) {
            Logger.getLogger(videoJMF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoPlayerException ex) {
            Logger.getLogger(videoJMF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CannotRealizeException ex) {
            Logger.getLogger(videoJMF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public float getVolume() {
        return mediaPlayer.getGainControl().getLevel();
    }

    public void setVolume(float volume) {
        mediaPlayer.getGainControl().setLevel(volume);
    }

    public void mute() {
        if (mediaPlayer.getGainControl().getMute()) {
            mediaPlayer.getGainControl().setMute(false);
        } else {
            mediaPlayer.getGainControl().setMute(true);
        }
    }

    public void stop() {
        mediaPlayer.stop();
        video.setVisible(false);
    }

    public void play() {
        mediaPlayer.start();
        video.setVisible(true);

    }

    public void pause(boolean isplay) {

        if (isplay) {
            Time pause = mediaPlayer.getMediaTime();
            mediaPlayer.setMediaTime(pause);
             mediaPlayer.stop();
            System.out.println("Pause: " + pause);
        } else {
            video.setVisible(false);
            mediaPlayer.start();
            video.setVisible(true);
        }


    }

    public void startAnimacao(final JProgressBar bar) {
        tempo = new Timer();
        task = new TimerTask() {

            public void run() {
                System.out.println(mediaPlayer.getMediaTime().getSeconds()+" - "+mediaPlayer.getDuration().getSeconds());
                frame = (int) Math.round((mediaPlayer.getMediaTime().getSeconds() * 100) / mediaPlayer.getDuration().getSeconds());

                if (mediaPlayer.getMediaTime().getSeconds() == mediaPlayer.getDuration().getSeconds()) {
                    frame = 100;
                    stop();
                    stopAnimacao(bar);
                } else {
                    System.out.println(frame);
                    bar.setValue(frame);

//                    System.out.println("tempo do video: "+ mediaPlayer.getDuration().getSeconds());
//                    System.out.println("rate: " +mediaPlayer.getMediaTime().getSeconds());
//                    System.out.println("tempo de reprodução: " + frame);
                }
            }
        };
        tempo.schedule(task, 0, speed);
    }

    public void stopAnimacao(final JProgressBar bar) {
        tempo.cancel();
        task.cancel();
        bar.setValue(0);

    }
}
