/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.io.File;

/**
 *
 * @author alexandre
 */

public class ListarDiretorio{
    public static void main(String[] args){

        File diretorio = new File("C:\\java");
        File[] arquivos = diretorio.listFiles();

        if(arquivos != null){
            int length = arquivos.length;

            for(int i = 0; i < length; ++i){
                File f = arquivos[i];

                if(f.isFile()){
                    System.out.println(f.getName());
                }
                else if(f.isDirectory()){
                    System.out.println("Diretorio: " + f.getName());
                }
            }
        }
    }
}
