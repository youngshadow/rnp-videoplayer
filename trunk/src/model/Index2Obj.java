/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 *
 * @author alexandre
 */
public class Index2Obj extends IndexXML {

    public static void main(String args[]) {
        XStream xstream = new XStream();
        String url = "C:\\Documents and Settings\\alexandre\\Meus documentos\\dcc119_aula9.index";
        File file = new File(url);

        try {
            InputStream is = new FileInputStream(file);
            IndexXML newXml = new IndexXML();
            newXml.setInd_item(new Ind_item());
            newXml.setMain(new main_class());



            newXml = (IndexXML) xstream.fromXML(is);

        } catch (FileNotFoundException ex) {
        }
    }
}
