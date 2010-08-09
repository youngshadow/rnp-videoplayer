/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videoplayer;

import com.sun.media.ui.MessageBox;
import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.MediaTimeSetEvent;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.PrefetchCompleteEvent;
import javax.media.RealizeCompleteEvent;
import javax.media.StopByRequestEvent;
import javax.media.Time;
import javax.swing.JPanel;

/**
 *
 * @author alexandre
 */
public class VideoController extends java.awt.Frame implements ControllerListener {

    private Player player;
    private URL url;
    private Component visual;
    private Component control = null;
    private JPanel container;
    private static double timeVideo;
    int videoWidth = 0;
    int videoHeight = 0;
    int controlHeight = 30;
    int insetWidth = 10;
    int insetHeight = 15;
    boolean firstTime = true;

    //ALerta
    public void alerta(String msg) {
        MessageBox MsgBox = new MessageBox("Erro!", msg);
    }

    public VideoController(String fileName, JPanel container) {

        try {
            if ((url = new URL("file:" + fileName)) == null) {
                alerta("Impossível abrir o arquivo:" + fileName);
                return;
            }
            this.container = null;
            this.container = container;
            visual = null;
            try {
                player = null;

                player = Manager.createPlayer(url);
                if (player != null) {

                    player.addControllerListener((ControllerListener) this);
                    player.realize();
                }

            } catch (MalformedURLException ex) {
                alerta("Impossível abrir o arquivo:1");
            }
        } catch (IOException ex) {
            alerta("Impossível abrir o arquivo:2");
        } catch (NoPlayerException ex) {
            alerta("Impossível abrir o arquivo:3" + fileName);
        }

    }

    public void controllerUpdate(ControllerEvent ce) {
        if (ce instanceof ControllerEvent) {

            if (ce instanceof StopByRequestEvent || ce instanceof MediaTimeSetEvent) {

                timeVideo = ce.getSourceController().getMediaTime().getSeconds();




                System.out.println("Tempo: " +  formatarTempo(timeVideo));
            }

        }
        if (ce instanceof RealizeCompleteEvent) {
            System.out.println("PrefetchCompleteEvent 1 ");
            player.prefetch();
        } else if (ce instanceof PrefetchCompleteEvent) {
            if (visual != null) {
                return;
            }
            if ((visual = player.getVisualComponent()) != null) {
                System.out.println("PrefetchCompleteEvent 2 ");
                Dimension size = visual.getPreferredSize();
                videoWidth = size.width;
                videoHeight = size.height;
                visual.setBounds(5, 5, videoWidth, videoHeight);
                container.add("Center", visual);
            } else {
                videoWidth = 320;
            }
            if ((control = player.getControlPanelComponent()) != null) {
                System.out.println("PrefetchCompleteEvent 3 ");
                controlHeight = control.getPreferredSize().height;
                control.setBounds(5, videoHeight + 8, videoWidth, controlHeight);
                container.setLayout(null);
                container.add("South", control);
                System.out.println("PrefetchCompleteEvent 4 ");
                container.setSize(videoWidth + insetWidth, videoHeight + controlHeight + insetHeight);
                validate();
                player.start();
            } else if (ce instanceof EndOfMediaEvent) {
                System.out.println("PrefetchCompleteEvent 5 ");
                player.setMediaTime(new Time(0));
                player.start();
            }
        }
    }

    /**
     * @return the timeVideo
     */
    public static double getTimeVideo() {
        return timeVideo;
    }

    /**
     * @param timeVideo the timeVideo to set
     */
    @SuppressWarnings("static-access")
    public void setTimeVideo(double timeVideo) {
        this.timeVideo = timeVideo;
    }

    // Método para formatar um valor
    public static String formatarTempo(double vlr) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("###,###,##0.00");
        return df.format(vlr);
    }
}
