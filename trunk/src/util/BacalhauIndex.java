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
