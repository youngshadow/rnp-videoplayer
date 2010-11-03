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
public class VerificaCaractere {


    public static String Verifica(String url) {
        // Create matcher on file
//        String value = "Fabio Quintana á !@#$%¨&*()_+{[}]A^^AÂ<>;:?/-+§¬¢£³²¹°ºêîôû";
        String host = "";
        boolean result = false;
        //* \s -- whitespace (espaço em branco)
        //* \S -- non-whitespace (não seja espaço em branco)
        //* \w -- word character [a-zA-Z0-9] (caractere de palavra)
        //* \W -- non-word character (não caractere de palavra)
        //* \p{Punct} -- punctuation (pontuação)
        //* \p{Lower} -- lowercase [a-z] (minúsculas)
        //* \p{Upper} -- uppercase [A-Z] (maiúsculas)

        /* aqui você defini qual o tipo de avaliação */
        Pattern pattern = Pattern.compile("\\W");
        /* passa a String a ser avaliada */
        Matcher matcher = pattern.matcher(url);

        // Find all matches
        while (matcher.find()) {
            // imprimi o retorno tratado
            host += matcher.group();
        }        
        return host;
    }
}
