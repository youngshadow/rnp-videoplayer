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

/**
 *
 * @author alexandre
 */
public class DAOSync {

    SlidesXML slides = new SlidesXML();

    public void gravarSlides(DefaultListModel listModel, String destino) {
        System.out.println("List" + listModel.getSize());

        for (int i = 0; i < listModel.getSize(); i++) {
            System.out.println("Item -->" + listModel.getElementAt(i));
            Slide slide = new Slide();
            slide.setRelative_path(listModel.getElementAt(i).toString());
            slide.setTime(listModel.getElementAt(i).toString());
            slides.setSlide(slide);
        }


        XStream xstream = new XStream(new XppDomDriver(new XmlFriendlyReplacer("_-", "_")));
        xstream.alias("slides", SlidesXML.class);
        xstream.alias("slide", Slide.class);
        xstream.addImplicitCollection(SlidesXML.class, "slide");
        // xstream.addImplicitCollection(SlidesXML.class, "teste");
        xstream.useAttributeFor(Slide.class, "relative_path");
        xstream.useAttributeFor(Slide.class, "time");

        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + xstream.toXML(slides);

        System.out.println("gravar: " + GravarArquivo.salvarArquivo(xml, destino+".sync"));
    }
}
