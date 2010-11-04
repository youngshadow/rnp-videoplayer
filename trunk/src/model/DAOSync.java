/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDomDriver;
import javax.swing.DefaultListModel;
import util.GravarArquivo;
import util.ValidaItem;

/**
 *
 * @author alexandre
 */
public class DAOSync {

    SlidesXML slides = new SlidesXML();
    boolean flag = true;
    private int aux;
    private String xml;

    public boolean gravarSlides(DefaultListModel listModel, String destino) {


        for (int i = 0; i < listModel.getSize(); i++) {
            Slide slide = new Slide();

            if(!ValidaItem.validar(listModel.getElementAt(i).toString())){
                flag = false;
            }

            slide.setRelative_path(listModel.getElementAt(i).toString().substring(listModel.getElementAt(i).toString().indexOf("-") + 1).trim());
            slide.setTime(formatarTempo(listModel.getElementAt(i).toString().substring(0, listModel.getElementAt(i).toString().indexOf("-")).trim()));
            slides.setSlide(slide);
        }


        XStream xstream = new XStream(new XppDomDriver(new XmlFriendlyReplacer("_-", "_")));
        xstream.alias("slides", SlidesXML.class);
        xstream.alias("slide", Slide.class);
        xstream.addImplicitCollection(SlidesXML.class, "slide");
        // xstream.addImplicitCollection(SlidesXML.class, "teste");
        xstream.useAttributeFor(Slide.class, "relative_path");
        xstream.useAttributeFor(Slide.class, "time");

         xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + xstream.toXML(slides);

//        if (flag != false) {
//            flag = GravarArquivo.salvarArquivo(getXml(), destino + ".sync");
//        }
        return flag;
    }

    public String formatarTempo(String tempo) {
        int minutos = Integer.parseInt(tempo.substring(3, 5));
        minutos = (minutos * 60) + Integer.parseInt(tempo.substring(6));

        if (minutos < aux) {
            flag = false;
        }
        aux = minutos;
        return minutos + "";
    }

    /**
     * @return the xml
     */
    public String getXml() {
        return xml;
    }
}
