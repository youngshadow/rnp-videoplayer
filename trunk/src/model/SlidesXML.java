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

public class SlidesXML {

    private ArrayList<Slide> slide = new ArrayList<Slide>();
   

    public ArrayList<Slide> getSlide() {
        return slide;
    }

    public void setSlide(Slide slide) {
        this.slide.add(slide);
    }

    /**
     * @param slide the slide to set
     */
    public void setSlide(ArrayList<Slide> slide) {
        this.slide = slide;
    }

  
}