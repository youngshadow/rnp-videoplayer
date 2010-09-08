/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videoplayer;

import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Format;
import javax.media.Manager;
import javax.media.MediaTimeSetEvent;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.PlugInManager;
import javax.media.PrefetchCompleteEvent;
import javax.media.RealizeCompleteEvent;
import javax.media.StopByRequestEvent;
import javax.media.Time;
import javax.media.format.VideoFormat;
import javax.swing.JOptionPane;
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
    //private static double timeVideo;
    private static String timeFormatado;
    int videoWidth = 0;
    int videoHeight = 0;
    int controlHeight = 30;
    int insetWidth = 10;
    int insetHeight = 15;
    int segundos;
    int minutos;
    int horas;
    boolean firstTime = true;

    //ALerta
    public void alerta(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Erro!", JOptionPane.ERROR_MESSAGE);
        return;
    }

    public VideoController() {
    }

    public VideoController(String fileName, JPanel container) {
//        Format [ ] inFormats = { new VideoFormat ( "MPEG" ) } ;
//        PlugInManager.addPlugIn("net.sourceforge.jffmpeg.VideoDecoder" , inFormats, null , PlugInManager.CODEC );
//        PlugInManager.addPlugIn("net.sourceforge.jffmpeg.AudioDecoder" , inFormats, null , PlugInManager.CODEC );
//        try {
//            PlugInManager.commit();
//        } catch (IOException ex) {
//            System.out.println("Erro ao abrir codec");
//            Logger.getLogger(VideoController.class.getName()).log(Level.SEVERE, null, ex);
//        }

        try {
            if ((url = new URL("file:" + fileName)) == null) {
                alerta("Impossível abrir o arquivo:" + fileName);
                return;
            }
            this.container = null;
            this.container = container;
            // visual.repaint();
            try {
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
                timeFormatado = timeFormat(ce.getSourceController().getMediaTime());
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
                container.removeAll();
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

                timeFormatado = timeFormat(ce.getSourceController().getMediaTime());
                timeFormat(player.getDuration());

            } else if (ce instanceof EndOfMediaEvent) {
                System.out.println("PrefetchCompleteEvent 5 ");
                player.setMediaTime(new Time(0));
                player.start();
            }
        }
    }

    public String timeFormat(Time ce) {
        //timeVideo = ce.getSourceController().getMediaTime().getSeconds();
        segundos = (int) TimeUnit.SECONDS.convert((long) ce.getSeconds(), TimeUnit.SECONDS);
        minutos = (int) TimeUnit.MINUTES.convert((long) ce.getSeconds(), TimeUnit.SECONDS);
        horas = (int) TimeUnit.HOURS.convert((long) ce.getSeconds(), TimeUnit.SECONDS);
        if (segundos > 60) {
            segundos = segundos % 60;
        }

        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        try {
            Date d = format.parse(horas + ":" + "" + minutos + ":" + segundos);
            SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");
            System.out.println("----> " + format2.format(d));
            return format2.format(d);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getTimeVideo() {

        return timeFormatado;
    }

    public int[] getDimensao() {
        int[] valores = new int[2];
        valores[0] = videoWidth;
        valores[1] = videoHeight;

        return valores;
    }

    public String tempoTotal() {
        return timeFormat(player.getDuration());
    }

    public void parar() {
        player.stop();
    }
}
