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
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
    private ArrayList<String>  filesNotFound = new ArrayList<String>();
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
                //verificando se slide existe no diretório
                if (!new File(url.substring(0,url.lastIndexOf(File.separator))+File.separator+slide.getRelative_path()).exists()) {
                   filesNotFound.add(slide.getRelative_path()+"\n");
            
            
        }

//                System.out.println("Nome do Arrquivo: "+url.substring(0,url.lastIndexOf(File.separator))+File.separator+slide.getRelative_path());
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
//        jtSlides1.setSelectionPath(treepath);
//        jtSlides1.startEditingAtPath(treepath);
    }

    /**
     * @return the filesNotFound
     */
    public ArrayList<String> getFilesNotFound() {
        return filesNotFound;
    }
}
