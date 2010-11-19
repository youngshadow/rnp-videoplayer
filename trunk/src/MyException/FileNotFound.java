/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MyException;

import java.util.ArrayList;

/**
 *
 * @author alexandre
 */
public class FileNotFound extends Exception {

    private ArrayList<String> filesNotFound;
    private String message;

    public FileNotFound(ArrayList<String> filesNotFound, String message) {
        this.filesNotFound = filesNotFound;
        this.message = message;
    }

    public ArrayList<String> getFilesNotFound() {
        return filesNotFound;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
