/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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

  
}


class Slide {

    private String time;
    private String relative_path;
    

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRelative_path() {
        return relative_path;
    }

    public void setRelative_path(String relative_path) {
        this.relative_path = relative_path;
    }

    
}
