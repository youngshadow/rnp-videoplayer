/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.thoughtworks.xstream.XStream;

/**
 *
 * @author alexandre
 */
public class main {

    public static void main(String[] args) {

        IndexXML index = new IndexXML();
        main_class main = new main_class();
        main.setNumber(1);
        main.setSub_title("Tipos de Dados, Variáveis e Comandos de Entrada e Saída");




        Ind_item subsubitem1 = new Ind_item();
        subsubitem1.setTime(111);
        subsubitem1.setText("subsubitem1");

        Ind_item subsubitem2 = new Ind_item();
        subsubitem2.setTime(222);
        subsubitem2.setText("subsubitem2");

        Ind_item subitem1 = new Ind_item();
        subitem1.setTime(11);
        subitem1.setText("subitem1");
        subitem1.setInd_item(subsubitem1);
        subitem1.setInd_item(subsubitem2);


        Ind_item subitem2 = new Ind_item();
        subitem2.setTime(22);
        subitem2.setText("subitem2");




        Ind_item iten1 = new Ind_item();
        iten1.setTime(1);
        iten1.setText("iten1");
        iten1.setInd_item(subitem1);
        

        Ind_item iten2 = new Ind_item();
        iten2.setTime(2);
        iten2.setText("iten2");
        iten2.setInd_item(subitem2);



        index.setMain(main);
        index.setInd_item(iten1);
        index.setInd_item(iten2);
        index.setMain_title("Algoritmos");

        //criando o XML
        XStream xstream = new XStream();
       // setando o nome da tag principal
        xstream.alias("index", IndexXML.class);
        xstream.alias("ind_item", Ind_item.class);

        //definindo o nome da tag
        xstream.aliasField("class", IndexXML.class, "mainClass");

        //omitindo a tag root Ind_item
        xstream.addImplicitCollection(IndexXML.class, "ind_item");
        xstream.addImplicitCollection(Ind_item.class, "ind_item");

        String xml = xstream.toXML(index);

        System.out.println("gravar"+ GravarArquivo.salvarArquivo(xml, "teste.xml"));

        System.out.println("Result");
        System.out.println(xml);

    }
}
