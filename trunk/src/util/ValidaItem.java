/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author alexandre
 */
public class ValidaItem {

    public static boolean validar(String item) {

        Pattern pattern = Pattern.compile("\\d{2}\\:\\d{2}\\:\\d{2}[\\s]?\\-\\W");
        Matcher matcher = pattern.matcher(item);
        return matcher.find();
    }

    public static void main(String args[]) {
        System.out.println(ValidaItem.validar("00:00:02 - novo tópico"));
    }
}
