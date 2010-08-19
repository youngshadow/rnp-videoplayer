/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javax.media.Time;

/**
 *
 * @author alexandre
 */
public class AulaXML {
    private String obj_filename;
    private long obj_filesize;
    private String obj_title;
    private String obj_type;
    private String professor;
    private String course;
    private String coursecode;
    private String grad_program;
    private String source;
    private long bitrate;
    private String duration;
    private Resolution resolution;
    private ArrayList<Rm_item> related_media = new ArrayList<Rm_item>();

    public String getObj_filename() {
        return obj_filename;
    }

    public void setObj_filename(String obj_filename) {
        this.obj_filename = obj_filename;
    }

    public long getObj_filesize() {
        return obj_filesize;
    }

    public void setObj_filesize(long obj_filesize) {
        this.obj_filesize = obj_filesize;
    }

    public String getObj_title() {
        return obj_title;
    }

    public void setObj_title(String obj_title) {
        this.obj_title = obj_title;
    }

    public String getObj_type() {
        return obj_type;
    }

    public void setObj_type(String obj_type) {
        this.obj_type = obj_type;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCoursecode() {
        return coursecode;
    }

    public void setCoursecode(String coursecode) {
        this.coursecode = coursecode;
    }

    public String getGrad_program() {
        return grad_program;
    }

    public void setGrad_program(String grad_program) {
        this.grad_program = grad_program;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public long getBitrate() {
        return bitrate;
    }

    public void setBitrate(long bitrate) {
        this.bitrate = bitrate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    public ArrayList<Rm_item> getRm_item() {
        return related_media;
    }

    public void setRm_item(Rm_item rm_item) {
        this.related_media .add(rm_item);
    }
 

}

class Resolution {
    private int r_x;
    private int r_y;

    public int getR_x() {
        return r_x;
    }

    public void setR_x(int r_x) {
        this.r_x = r_x;
    }

    public int getR_y() {
        return r_y;
    }

    public void setR_y(int r_y) {
        this.r_y = r_y;
    }

   
}

//class Related_media {
//
//    private ArrayList<Rm_item> rm_item = new ArrayList<Rm_item>();
//
//    public ArrayList<Rm_item> getRm_item() {
//        return rm_item;
//    }
//
//    public void setRm_item(Rm_item rm_item) {
//        this.rm_item.add(rm_item);
//    }
//}

class Rm_item {
    private String rm_filename;
    private String rm_type;

    public String getRm_filename() {
        return rm_filename;
    }

    public void setRm_filename(String rm_filename) {
        this.rm_filename = rm_filename;
    }

    public String getRm_type() {
        return rm_type;
    }

    public void setRm_type(String rm_type) {
        this.rm_type = rm_type;
    }
}
