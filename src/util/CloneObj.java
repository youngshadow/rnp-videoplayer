/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author alexandre
 */
public class CloneObj {
      public static Serializable getClone(Serializable objeto) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos;
        ObjectOutputStream os = new ObjectOutputStream(baos = new ByteArrayOutputStream());
        try {
            os.writeObject(objeto);
            os.flush();
        } finally {
            os.close();
        }

        ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        try {
            return (Serializable) is.readObject();
        } finally {
            is.close();
        }
    }

}
