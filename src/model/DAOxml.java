/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDomDriver;
import util.GravarArquivo;

/**
 *
 * @author alexandre
 */
public class DAOxml {

    private AulaXML aulas = new AulaXML();
    private Resolution resolution;
    private Rm_item rm_item_index = new Rm_item();
    private Rm_item rm_item_video = new Rm_item();
    private Rm_item rm_itemsync = new Rm_item();

    public void gravarXML(String destino) {

        aulas.setRm_item(rm_item_index);
        aulas.setRm_item(rm_item_video);
        aulas.setRm_item(rm_itemsync);

        XStream xstream = new XStream(new XppDomDriver(new XmlFriendlyReplacer("_-", "_")));

        xstream.alias("rio_object", AulaXML.class);
        xstream.alias("rm_item", Rm_item.class);
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<!DOCTYPE rio_object SYSTEM \"object.dtd\">\n" + xstream.toXML(aulas);

        GravarArquivo.salvarArquivo(xml, destino + ".xml");
    }

    public AulaXML getAulas() {
        return aulas;
    }

    public void setAulas(AulaXML aulas) {
        this.aulas = aulas;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    public void setRm_item_index(String arquivoIndex) {
        this.rm_item_index.setRm_filename(arquivoIndex);
        this.rm_item_index.setRm_type("index");
    }

    public void setRm_item_video(String nomeVideo) {
        this.rm_item_video.setRm_filename(nomeVideo);
        this.rm_item_video.setRm_type("video");
    }

    public void setRm_itemsync(String arquivoSync) {
        this.rm_itemsync.setRm_filename(arquivoSync);
        this.rm_itemsync.setRm_type("sync");
    }
}
