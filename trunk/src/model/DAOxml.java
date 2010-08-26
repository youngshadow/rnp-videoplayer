/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author alexandre
 */
public class DAOxml {
    private AulaXML aulas = new AulaXML();
    private Resolution resolution = new Resolution();
    private Rm_item rm_item = new Rm_item();


    public void setAulas(AulaXML aulas) {
        this.aulas = aulas;
    }


    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }


    public void setRm_item(Rm_item rm_item) {
        this.rm_item = rm_item;
    }



}
