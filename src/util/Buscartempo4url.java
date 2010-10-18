/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import java.net.URL;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;

/**
 *
 * @author alexandre
 */
public class Buscartempo4url {

    public static long buscarTempo(String s_url) {
        try {
            URL url = new URL(s_url);
            Player player = Manager.createPlayer(url);
            System.out.println("duracao: " + player.getDuration().getNanoseconds());
            return player.getDuration().getNanoseconds();

        } catch (IOException ex) {
        } catch (NoPlayerException ex) {
        }
        System.exit(0);
        return 0;
    }
}
