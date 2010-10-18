/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDomDriver;
import java.io.InputStream;

/**
 *
 * @author alexandre
 */
public class Xml2Obj extends AulaXML {

    public Xml2Obj(InputStream is) {
        XStream xstream = new XStream(new XppDomDriver(new XmlFriendlyReplacer("_-", "_")));

        xstream.alias("rio_object", AulaXML.class);
        xstream.alias("rm_item", Rm_item.class);

        AulaXML aulaxml = (AulaXML) xstream.fromXML(is);
        setBitrate(aulaxml.getBitrate());
        setCourse(aulaxml.getCourse());
        setCoursecode(aulaxml.getCoursecode());
        setDuration(aulaxml.getDuration());
        setGrad_program(aulaxml.getGrad_program());
        setObj_filename(aulaxml.getObj_filename());
        setObj_filesize(aulaxml.getObj_filesize());
        setObj_title(aulaxml.getObj_title());
        setObj_type(aulaxml.getObj_type());
        setProfessor(aulaxml.getProfessor());
        // setResolution(aulaxml.getResolution());
        // setRm_item(aulaxml.getRm_item());
        setSource(aulaxml.getSource());


    }
}
