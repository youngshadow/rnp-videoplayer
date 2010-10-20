/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author alexandre
 */
public class BacalhauIndex {

    private String xmlFinal;

    public BacalhauIndex(String url) {

        StringBuffer xml = new StringBuffer();
        File file = new File(url);
        try {
            FileInputStream in = new FileInputStream(file);
            Scanner scanner = new Scanner(in);
            while (scanner.hasNext()) {
                xml.append(scanner.nextLine());
            }
            xmlFinal = xml.toString().trim();
            System.out.println("antes\n" + xmlFinal);
           // xmlFinal = xmlFinal.replaceAll("<ind_item>", "<ind_item> <ind_item>");
//            xmlFinal = xmlFinal.replaceFirst("<ind_item>", "<ind_item> <ind_item>");
//            xmlFinal = xmlFinal.replaceAll("</text>", "</text><ind_item/>");
//            xmlFinal = xmlFinal.replaceAll("</index>", "</ind_item></index>");
            System.out.println("depois \n" + xmlFinal);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        BacalhauIndex bac = new BacalhauIndex("G:\\UFJF\\AULAS_PRONTAS\\dcc119_aula3\\dcc119_aula3.index");
    }

    /**
     * @return the xmlFinal
     */
    public String getXmlFinal() {
        return xmlFinal;
    }
}
