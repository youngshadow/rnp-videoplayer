/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.ArrayList;

/**
 *
 * @author alexandre
 */
@XStreamAlias("index")
public class IndexXML {

    private String main_title;
    private main_class mainClass;
    private ArrayList<Ind_item> ind_item = new ArrayList<Ind_item>();

    public String getMain_title() {
        return main_title;
    }

    public void setMain_title(String main_title) {
        this.main_title = main_title;
    }

    public main_class getMain() {
        return mainClass;
    }

    public void setMain(main_class main) {
        this.mainClass = main;
    }

    public ArrayList<Ind_item> getInd_item() {
        return ind_item;
    }

    public void setInd_item(Ind_item ind_item1) {
        this.ind_item.add(ind_item1);
    }
}

class main_class {

    private int number;
    private String sub_title;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }
}

@XStreamAlias("ind_item")
class Ind_item {

    private String time;
    private String text;
    private ArrayList<Ind_item> ind_item = new ArrayList<Ind_item>();

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

   public ArrayList<Ind_item> getInd_item() {
        return ind_item;
    }

    public void setInd_item(Ind_item ind_item1) {
        this.ind_item.add(ind_item1);
    }
}
