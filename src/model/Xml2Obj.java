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

        setRm_item(aulaxml.getRm_item());

        setSource(aulaxml.getSource());


    }
}
