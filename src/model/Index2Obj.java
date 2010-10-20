/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDomDriver;
import util.BacalhauIndex;

/**
 *
 * @author alexandre
 */
public class Index2Obj extends IndexXML {

     public Index2Obj(String url) {

        //criando o XML e formatando underline simples
        XStream xstream = new XStream(new XppDomDriver(new XmlFriendlyReplacer("_-", "_")));
       // setando o nome da tag principal
        xstream.alias("index", IndexXML.class);
        xstream.alias("ind_item", Ind_item.class);

        //definindo o nome da tag
        xstream.aliasField("class", IndexXML.class, "mainClass");

        Object indexXml = (Object) xstream.fromXML(new BacalhauIndex(url).getXmlFinal());
//        IndexXML indexXml = (IndexXML) xstream.fromXML(new BacalhauIndex(url).getXmlFinal());

       
    }
}
