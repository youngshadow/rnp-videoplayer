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
public class BacalhauSync {

    private String xmlFinal;

    public BacalhauSync(String url) {

          StringBuffer xml = new StringBuffer();
        File file = new File(url);
        try {
            FileInputStream in = new FileInputStream(file);
            Scanner scanner = new Scanner(in);
            while (scanner.hasNext()) {
                xml.append(scanner.nextLine());
            }
            xmlFinal = xml.toString();
            System.out.println("antes\n"+xmlFinal);
            xmlFinal = xmlFinal.replaceAll("<slides>", "<slides>  <slide>");
            xmlFinal = xmlFinal.replaceAll("</slides>", "</slide> </slides>");
            System.out.println("depois \n"+xmlFinal);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

public static void main(String args[]){

    BacalhauSync bac = new BacalhauSync("C:\\Documents and Settings\\alexandre\\Meus documentos\\dcc119_aula9.sync");
}

    /**
     * @return the xmlFinal
     */
    public String getXmlFinal() {
        return xmlFinal;
    }
   
}
