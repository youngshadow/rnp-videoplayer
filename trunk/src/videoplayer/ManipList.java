/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videoplayer;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Enumeration;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author bisaggio
 */
public class ManipList {

       private Vector urlAbsoluta;
    private Vector nomeArqivo;
    private static FileNameExtensionFilter extencoes = new FileNameExtensionFilter("Arquivo de Vídeo", "mpeg", "mpg");

    public ManipList() {
        urlAbsoluta = new Vector(10, 2);
        nomeArqivo = new Vector(10, 2);
    }

    public void setVideo(URL urlAbsoluta, String nomeArqivo) {
        this.urlAbsoluta.addElement(urlAbsoluta);
        this.nomeArqivo.addElement(nomeArqivo);
    }

    //remove do vector urlAbsoluta a ocorrencia do nome selecionado
    public void removeVideo(String nomeArqivo) {
        this.nomeArqivo.removeElement(nomeArqivo);
        Enumeration e = this.nomeArqivo.elements();
        while (e.hasMoreElements()) {
            String elemento = (String) e.nextElement();
            if (elemento.equals(nomeArqivo)) {
                urlAbsoluta.remove(elemento);
                break;
            }
        }
    }
    //Busca a URL do ítem selecionado

    public URL getURL(String nomeArquivo) {
        Enumeration e = this.nomeArqivo.elements();
        URL url = null;
        int d = 0;
        while (e.hasMoreElements()) {
            String elemento = (String) e.nextElement();
            if (elemento.equals(nomeArquivo)) {
                url = (URL) urlAbsoluta.elementAt(d);
            }
            d++;
        }
        return url;
    }

    public URL ultimaURL(int d) {
        URL url = (URL) urlAbsoluta.elementAt(d);
        return url;
    }

    public void dialogo(DefaultListModel model, String tempo) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(extencoes);
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            URI uri = fileChooser.getSelectedFile().toURI();
            try {
                URL url = uri.toURL();
                String nomeFile = fileChooser.getSelectedFile().getName();

                setVideo(url, nomeFile);
                model.addElement(tempo+" - "+nomeFile);

            } catch (MalformedURLException ex) {
                Logger.getLogger(PlayList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
