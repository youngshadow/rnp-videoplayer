/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author alexandre
 */
public class Index2Obj extends IndexXML {

    private DefaultTreeModel jtreeModel;
    private JTree jtTopicos;
    DefaultMutableTreeNode rootTopic = new DefaultMutableTreeNode("Roteiro");

    public static void main(String args[]) {
        new Index2Obj("G:\\UFJF\\AULAS_PRONTAS\\dcc119_aula1\\dcc119_aula1.index", new JTree(), new DefaultTreeModel(null));
    }

    public Index2Obj(String url, JTree jtTopicos, DefaultTreeModel jtreeModel) {

        this.jtTopicos = jtTopicos;
        this.jtreeModel = jtreeModel;
        gerarRoot();



        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
       factory.setAttribute("http://xml.org/sax/features/validation", false);
        factory.setAttribute("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
        factory.setAttribute("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document docs = null;
            docs = builder.parse(new File(url));
            Element elem = docs.getDocumentElement();

            //Element tagMain_title = Index2Obj.getElemento(elem, "main_title");
            //System.out.println("--> " + tagMain_title.getFirstChild().getNodeValue());

            //Element tagClass = Index2Obj.getElemento(elem, "class");
//            System.out.println("2-> " + tagClass.hasChildNodes());
//            System.out.println("3-> " + getChildTagValue(tagClass, "number"));
//            System.out.println("3-> " + getChildTagValue(tagClass, "sub_title"));

            for (int i = 0; i < elem.getChildNodes().getLength(); i++) {
                Node no = elem.getChildNodes().item(i);
                if (no.getNodeName().equalsIgnoreCase("ind_item")) {
                    System.out.println("== -> " + no.getChildNodes().item(3).getChildNodes().item(0).getNodeValue());

                    DefaultMutableTreeNode newTopic = new DefaultMutableTreeNode(secondsToString(Double.parseDouble(no.getChildNodes().item(1).getChildNodes().item(0).getNodeValue()))+" - "+no.getChildNodes().item(3).getChildNodes().item(0).getNodeValue());
                    jtreeModel.insertNodeInto(newTopic, rootTopic, rootTopic.getChildCount());
                    TreeNode[] nodes = jtreeModel.getPathToRoot(newTopic);
                    TreePath treepath = new TreePath(nodes);
                    jtTopicos.scrollPathToVisible(treepath);

                    varrerFilhos(no,newTopic);
                }

            }

        } catch (Exception ex) {
            Logger.getLogger(Index2Obj.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void varrerFilhos(Node no,DefaultMutableTreeNode newTopic) throws DOMException {
        for (int j = 0; j < no.getChildNodes().getLength(); j++) {
            Node no1 = no.getChildNodes().item(j);
            if (no1.getNodeName().equalsIgnoreCase("ind_item")) {
                System.out.println("99 -> " + no1.getChildNodes().item(3).getChildNodes().item(0).getNodeValue());
                 DefaultMutableTreeNode newTopic1 = new DefaultMutableTreeNode(secondsToString(Double.parseDouble(no1.getChildNodes().item(1).getChildNodes().item(0).getNodeValue()))+" - "+no1.getChildNodes().item(3).getChildNodes().item(0).getNodeValue());
                    jtreeModel.insertNodeInto(newTopic1, newTopic, newTopic.getChildCount());
                    TreeNode[] nodes = jtreeModel.getPathToRoot(newTopic1);
                    TreePath treepath = new TreePath(nodes);
                    jtTopicos.scrollPathToVisible(treepath);
                varrerFilhos(no1,newTopic1);
            }
        }
    }


     private static String secondsToString(Double seconds) {
        final Integer minutes = seconds.intValue() / 60;
        return "00:" + zeroPad((int) minutes, 2) + ":" + zeroPad((int) (seconds % 60), 2);
    }
     
       private static String zeroPad(int i, int len) {
        String result = Integer.toString(i);
        while (result.length() < len) {
            result = "0" + result;
        }
        return result;
    }

    public static Element getElemento(Element documento, String nomeElemento) {

        Element tagTransp = null;

        NodeList transp = documento.getElementsByTagName(nomeElemento);


        if (transp.getLength() > 0) {
            tagTransp = (Element) transp.item(0);


        }

        return tagTransp;


    }

    private static String getChildTagValue(Element elem, String tagName) throws Exception {

        String resposta = "";


        if (elem != null) {
            if (elem.getElementsByTagName(tagName) == null) {
                return null;


            }
            NodeList children = elem.getElementsByTagName(tagName);


            if (children == null) {
                return null;


            }

            Element child = (Element) children.item(0);



            if (child == null || child.getFirstChild() == null) {
                return null;


            }
            resposta = child.getFirstChild().getNodeValue();


        }
        return resposta;

    }

    protected void gerarRoot() {

        jtTopicos.setModel(jtreeModel);
        jtreeModel.setRoot(rootTopic);
        TreeNode[] nodes = jtreeModel.getPathToRoot(rootTopic);
        TreePath treepath = new TreePath(nodes);
        jtTopicos.scrollPathToVisible(treepath);
        jtTopicos.setSelectionPath(treepath);
        jtTopicos.startEditingAtPath(treepath);
    }
}
