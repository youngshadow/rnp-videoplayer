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
public class DAOxml {

    IndexXML index = new IndexXML();
    main_class main = new main_class();
    TreeNode treeNode;

    public void gravarTopicos(TreeNode treeNode1) {
        this.treeNode = treeNode1;


        System.out.println("nó:" + treeNode);

        if (!treeNode.toString().equals("root")) {
            Ind_item item = new Ind_item();
            item.setText(treeNode.toString());
            item.setTime(01);

            if (treeNode.getChildCount() > 0) {
                buscarFilho(treeNode, item);
                Enumeration filho = treeNode.children();
                treeNode = (TreeNode) filho.nextElement();

            }
            System.out.println("tem filho: " + treeNode.getChildCount());
            index.setInd_item(item);

        }

        //recursividade
        for (Enumeration filho = treeNode.children(); filho.hasMoreElements();) {

            gravarTopicos((TreeNode) filho.nextElement());

// ################## gerando o xml
            if (filho.hasMoreElements() == false) {
                main.setSub_title("Tipos de Dados, Variáveis e Comandos de Entrada e Saída");

                index.setMain(main);
                index.setMain_title("Algoritmos");

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

                System.out.println("gravar: " + GravarArquivo.salvarArquivo(xml, "teste.xml"));
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
            item1.setText(filho.toString());
            item1.setTime(i);
            item.setInd_item(item1);

            if (filho.getChildCount() > 0) {
                buscarFilho(filho, item1);
                Enumeration filho1 = this.treeNode.children();
                this.treeNode = (TreeNode) filho1.nextElement();
            }
        }
//        Enumeration filho1 = treeNode.children();
//        this.treeNode = (TreeNode) filho1.nextElement();
    }
}
