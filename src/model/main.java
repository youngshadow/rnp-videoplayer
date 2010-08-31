/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import util.GravarArquivo;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDomDriver;
/**
 *
 * @author alexandre
 */
public class main {

    public static void main(String[] args) {

//        IndexXML index = new IndexXML();
//        main_class main = new main_class();
//        main.setNumber(1);
//        main.setSub_title("Tipos de Dados, Variáveis e Comandos de Entrada e Saída");
//
//
//
//
//        Ind_item subsubitem1 = new Ind_item();
//        subsubitem1.setTime(111);
//        subsubitem1.setText("subsubitem1");
//
//        Ind_item subsubitem2 = new Ind_item();
//        subsubitem2.setTime(222);
//        subsubitem2.setText("subsubitem2");
//
//        Ind_item subitem1 = new Ind_item();
//        subitem1.setTime(11);
//        subitem1.setText("subitem1");
//        subitem1.setInd_item(subsubitem1);
//        subitem1.setInd_item(subsubitem2);
//
//
//        Ind_item subitem2 = new Ind_item();
//        subitem2.setTime(22);
//        subitem2.setText("subitem2");
//
//        Ind_item iten1 = new Ind_item();
//        iten1.setTime(1);
//        iten1.setText("iten1");
//        iten1.setInd_item(subitem1);
//
//        Ind_item iten2 = new Ind_item();
//        iten2.setTime(2);
//        iten2.setText("iten2");
//        iten2.setInd_item(subitem2);
//
//        index.setMain(main);
//        index.setInd_item(iten1);
//        index.setInd_item(iten2);
//        index.setMain_title("Algoritmos");
//
//        //criando o XML e formatando underline simples
//        XStream xstream = new XStream(new XppDomDriver(new XmlFriendlyReplacer("_-", "_")));
//       // setando o nome da tag principal
//        xstream.alias("index", IndexXML.class);
//        xstream.alias("ind_item", Ind_item.class);
//
//        //definindo o nome da tag
//        xstream.aliasField("class", IndexXML.class, "mainClass");
//
//        //omitindo a tag root Ind_item
//        xstream.addImplicitCollection(IndexXML.class, "ind_item");
//        xstream.addImplicitCollection(Ind_item.class, "ind_item");
//
//        String xml ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<!DOCTYPE index SYSTEM \"index.dtd\">" + xstream.toXML(index);
//
//        System.out.println("gravar: "+ GravarArquivo.salvarArquivo(xml, "teste.xml"));
//
//        System.out.println("Result: ");
//        System.out.println(xml);


//        ********************************************** //

//        SlidesXML slides = new SlidesXML();
//
//
//        for (int i =0; i< 5; i++){
//            Slide slide = new Slide();
//            slide.setRelative_path("path"+i);
//            slide.setTime(""+i);
//            slides.setSlide(slide);
//           // slide.setTeste("");
//
//        }
//
//        XStream xstream = new XStream(new XppDomDriver(new XmlFriendlyReplacer("_-", "_")));
//        xstream.alias("slides", SlidesXML.class);
//        xstream.alias("slide", Slide.class);
//        xstream.addImplicitCollection(SlidesXML.class, "slide");
//       // xstream.addImplicitCollection(SlidesXML.class, "teste");
//        xstream.useAttributeFor(Slide.class, "relative_path");
//        xstream.useAttributeFor(Slide.class, "time");
//
//        System.out.println(xstream.toXML(slides));



        //          ********************************************** //


        AulaXML aulas = new AulaXML();
       
        Rm_item rm_item = new Rm_item();
        Rm_item rm_item2= new Rm_item();
        Rm_item rm_item3= new Rm_item();

        rm_item.setRm_filename("rm name");
        rm_item.setRm_type("rm type");

        rm_item2.setRm_filename("rm name222");
        rm_item2.setRm_type("rm type222");

        rm_item3.setRm_filename("rm name333");
        rm_item3.setRm_type("rm type333");



        aulas.setBitrate("123");
        aulas.setCourse("curso");
        aulas.setCoursecode("cod123");
        aulas.setDuration("22:22");
        aulas.setGrad_program("grede");
        aulas.setObj_filename("file");
        aulas.setObj_title("title");
        aulas.setObj_type("tipo");
        aulas.setProfessor("professor");
        aulas.setResolution(320, 440);
        aulas.setSource("source");
        aulas.setRm_item(rm_item);
        aulas.setRm_item(rm_item2);
        aulas.setRm_item(rm_item3);

        

        XStream xstream = new XStream(new XppDomDriver(new XmlFriendlyReplacer("_-", "_")));

        xstream.alias("rio_object", AulaXML.class);
        xstream.alias("rm_item", Rm_item.class);
        System.out.println(xstream.toXML(aulas));
//
//
//
    }
}