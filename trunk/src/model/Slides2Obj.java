/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDomDriver;
import java.io.File;
import javax.swing.JOptionPane;
import util.BacalhauSync;

/**
 *
 * @author alexandre
 */
public class Slides2Obj extends SlidesXML {

    public Slides2Obj(String url) {

          if (!new File(url).exists()) {
            JOptionPane.showMessageDialog(null, "O Arquivo \n " + url + "\n não foi encontrado", "Alerta", JOptionPane.ERROR_MESSAGE);
            return;
        }

        XStream xstream = new XStream(new XppDomDriver(new XmlFriendlyReplacer("_-", "_")));
        xstream.alias("slides", SlidesXML.class);
        xstream.alias("slide", Slide.class);
        
        xstream.useAttributeFor(Slide.class, "relative_path");
        xstream.useAttributeFor(Slide.class, "time");

        SlidesXML slidesXml = (SlidesXML) xstream.fromXML(new BacalhauSync(url).getXmlFinal());
        setSlide(slidesXml.getSlide());
        //setSlide();
    }

}
