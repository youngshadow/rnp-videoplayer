/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDomDriver;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.tree.TreeNode;
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
    private TreeNode treeNode;

    public boolean gravarSlides(TreeNode tree) {
        treeNode = tree;

        Enumeration filho = treeNode.children();

        while (filho.hasMoreElements()) {
            Slide slide = new Slide();
            treeNode = (TreeNode) filho.nextElement();
            if (!ValidaItem.validar(treeNode.toString())) {
                flag = false;
                return false;
            }


            Pattern pattern = Pattern.compile("\\S\\W");
//         Pattern pattern = Pattern.compile("\\d{2}\\:\\d{2}\\:\\d{2}[\\s]?\\-\\W");
            Matcher matcher = pattern.matcher(treeNode.toString().substring(treeNode.toString().indexOf("-") + 1,treeNode.toString().indexOf(".")).trim());
            if(matcher.find()){
                JOptionPane.showMessageDialog(null, "Caracteres inválidos em: \n" + treeNode.toString().substring(treeNode.toString().indexOf("-") + 1,treeNode.toString().indexOf(".")).trim(), "Erro!", JOptionPane.ERROR_MESSAGE);
               
                 flag = false;
                return false;
            }


            slide.setRelative_path(treeNode.toString().substring(treeNode.toString().indexOf("-") + 1).trim());
            slide.setTime(formatarTempo(treeNode.toString().substring(0, treeNode.toString().indexOf("-")).trim()));
            slides.setSlide(slide);
            System.out.println("treeSlide -> " + treeNode.toString().substring(treeNode.toString().indexOf("-") + 1).trim() + " ||| " + formatarTempo(treeNode.toString().substring(0, treeNode.toString().indexOf("-")).trim()));
        }

        XStream xstream = new XStream(new XppDomDriver(new XmlFriendlyReplacer("_-", "_")));
        xstream.alias("slides", SlidesXML.class);
        xstream.alias("slide", Slide.class);
        xstream.addImplicitCollection(SlidesXML.class, "slide");
        // xstream.addImplicitCollection(SlidesXML.class, "teste");
        xstream.useAttributeFor(Slide.class, "relative_path");
        xstream.useAttributeFor(Slide.class, "time");

        xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + xstream.toXML(slides);
        return flag;
    }

    public boolean gravarSlides(DefaultListModel listModel, String destino) {


        for (int i = 0; i < listModel.getSize(); i++) {
            Slide slide = new Slide();

            if (!ValidaItem.validar(listModel.getElementAt(i).toString())) {
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
