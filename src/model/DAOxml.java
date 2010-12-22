/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * Copyright (C) 2009, Edmundo Albuquerque de Souza e Silva.
 *
 * This file may be distributed under the terms of the Q Public License
 * as defined by Trolltech AS of Norway and appearing in the file
 * LICENSE.QPL included in the packaging of this file.
 *
 * THIS FILE IS PROVIDED AS IS WITH NO WARRANTY OF ANY KIND, INCLUDING
 * THE WARRANTY OF DESIGN, MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE.  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL,
 * INDIRECT OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING
 * FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT,
 * NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION
 * WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *
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

    public boolean gravarXML(String destino) {

        aulas.setRm_item(rm_item_index);
        aulas.setRm_item(rm_item_video);
        aulas.setRm_item(rm_itemsync);

        XStream xstream = new XStream(new XppDomDriver(new XmlFriendlyReplacer("_-", "_")));

        xstream.alias("rio_object", AulaXML.class);
        xstream.alias("rm_item", Rm_item.class);
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<!DOCTYPE rio_object SYSTEM \"object.dtd\">\n" + xstream.toXML(aulas);

       return  GravarArquivo.salvarArquivo(xml, destino + ".xml");
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
