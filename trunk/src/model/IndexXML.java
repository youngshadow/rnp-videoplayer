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
