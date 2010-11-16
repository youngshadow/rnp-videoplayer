/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDomDriver;
import java.io.File;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import util.BacalhauSync;

/**
 *
 * @author alexandre
 */
public class Slides2Obj extends SlidesXML {

    private DefaultTreeModel jtreeModel1;
    private JTree jtSlides1;
    DefaultMutableTreeNode rootTopic = new DefaultMutableTreeNode("Roteiro");

    public Slides2Obj(String url, JTree jtSlides, DefaultTreeModel jtreeModel) {
        this.jtSlides1 = jtSlides;
        this.jtreeModel1 = jtreeModel;

        gerarRoot();
        DefaultMutableTreeNode nodeSelect = (DefaultMutableTreeNode) jtSlides1.getModel().getRoot();


        DecimalFormat df = new DecimalFormat("00");

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



        for (Slide slide : slidesXml.getSlide()) {
            Double tempo = Double.parseDouble(slide.getTime());
            if (nodeSelect != null) {
                DefaultMutableTreeNode newTopic = new DefaultMutableTreeNode("00:" + df.format(tempo.intValue() / 60) + ":" + df.format(tempo.intValue() % 60) + " - " + slide.getRelative_path());
                jtreeModel1.insertNodeInto(newTopic, nodeSelect, nodeSelect.getChildCount());
                TreeNode[] nodes = jtreeModel1.getPathToRoot(newTopic);
                TreePath treepath = new TreePath(nodes);
                jtSlides1.scrollPathToVisible(treepath);
            }
        }
    }


     protected void gerarRoot() {

        jtSlides1.setModel(jtreeModel1);
        jtreeModel1.setRoot(rootTopic);
        TreeNode[] nodes = jtreeModel1.getPathToRoot(rootTopic);
        TreePath treepath = new TreePath(nodes);
        jtSlides1.scrollPathToVisible(treepath);
        jtSlides1.setSelectionPath(treepath);
        jtSlides1.startEditingAtPath(treepath);
    }
}
