/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author alexandre
 */
public class Index2Obj extends IndexXML {

    public static void main(String args[]) {
        new Index2Obj("G:\\UFJF\\AULAS_PRONTAS\\dcc119_aula3\\dcc119_aula3.index");
    }

    public Index2Obj(String url) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document docs = null;
            docs = builder.parse(new File(url));
            Element elem = docs.getDocumentElement();

            Element tagMain_title = Index2Obj.getElemento(elem, "main_title");
            System.out.println("--> "+ tagMain_title.getFirstChild().getNodeValue());

            Element tagClass = Index2Obj.getElemento(elem, "class");
            System.out.println("2-> "+ tagClass.hasChildNodes());
            System.out.println("3-> "+ getChildTagValue(tagClass, "number"));
            System.out.println("3-> "+ getChildTagValue(tagClass, "sub_title"));

            Element tagInd_item = Index2Obj.getElemento(elem, "ind_item");
            System.out.println("4-> "+ getChildTagValue(tagInd_item, "text"));
            System.out.println("4-> "+ tagInd_item.getChildNodes().getLength());




            
        } catch (Exception ex) {
            Logger.getLogger(Index2Obj.class.getName()).log(Level.SEVERE, null, ex);
        }



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
}
