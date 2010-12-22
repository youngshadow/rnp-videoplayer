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
