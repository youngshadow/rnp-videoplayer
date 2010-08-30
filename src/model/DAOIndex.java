package model;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDomDriver;
import java.util.Enumeration;
import javax.swing.tree.TreeNode;
import util.GravarArquivo;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alexandre
 */
public class DAOIndex {

    IndexXML index = new IndexXML();
    main_class main = new main_class();
    TreeNode treeNode;
    private String titulo;
    private String subTitulo;
    private String destino;

    public void gravarTopicos(TreeNode treeNode1, String titulo, String subTitulo, String destino, String textoRoot) {
        this.treeNode = treeNode1;
        this.destino = destino;

        if (!treeNode.toString().equals(textoRoot)) {
            Ind_item item = new Ind_item();
            item.setText(treeNode.toString().substring(treeNode.toString().indexOf("-")+1));
            item.setTime(treeNode.toString().substring(0, treeNode.toString().indexOf("-")));

            if (treeNode.getChildCount() > 0) {
                buscarFilho(treeNode, item);
                if (!this.treeNode.isLeaf()) {
                    Enumeration filho = treeNode.children();
                    treeNode = (TreeNode) filho.nextElement();
                }
            }

            index.setInd_item(item);
        }

        //recursividade
        for (Enumeration filho = treeNode.children(); filho.hasMoreElements();) {

            gravarTopicos((TreeNode) filho.nextElement(), titulo, subTitulo, this.destino, textoRoot);

// ################## gerando o xml
            if (filho.hasMoreElements() == false) {


                index.setMain(main);
                index.setMain_title(titulo);
                main.setSub_title(subTitulo);

                //criando o XML e formatando underline simples
                XStream xstream = new XStream(new XppDomDriver(new XmlFriendlyReplacer("_-", "_")));
                // setando o nome da tag principal
                xstream.alias("index", IndexXML.class);
                xstream.alias("ind_item", Ind_item.class);

                //definindo o nome da tag
                xstream.aliasField("class", IndexXML.class, "mainClass");

                //omitindo a tag root Ind_item
                xstream.addImplicitCollection(IndexXML.class, "ind_item");
                xstream.addImplicitCollection(Ind_item.class, "ind_item");

                String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<!DOCTYPE index SYSTEM \"index.dtd\">\n" + xstream.toXML(index);

                System.out.println("gravar: " + GravarArquivo.salvarArquivo(xml, this.destino + ".index"));
                System.out.println("Result: ");
                System.out.println(xml);
            }
        }
    }

// método recursivo para tratar os filhos
    private void buscarFilho(TreeNode treeNode, Ind_item item) {
        for (int i = 0; i < treeNode.getChildCount(); i++) {
            TreeNode filho = treeNode.getChildAt(i);
            Ind_item item1 = new Ind_item();
            item.setText(treeNode.toString().substring(treeNode.toString().indexOf("-")));
            item.setTime(treeNode.toString().substring(0, treeNode.toString().indexOf("-")));
            item.setInd_item(item1);

            if (filho.getChildCount() > 0) {
                buscarFilho(filho, item1);
                if (!this.treeNode.isLeaf()) {
                    Enumeration filho1 = this.treeNode.children();
                    this.treeNode = (TreeNode) filho1.nextElement();
                }
            }
        }
//        Enumeration filho1 = treeNode.children();
//        this.treeNode = (TreeNode) filho1.nextElement();
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @param subTitulo the subTitulo to set
     */
    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }
}
