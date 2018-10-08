/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package praktikum03;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class SerializationDemo {

    public void serialize(List<Participant> pList, String fileName) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(pList);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public List<Participant> deserialize(String filename) throws ClassNotFoundException {
        List<Participant> pList = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            pList = (List<Participant>) in.readObject();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        return pList;
    }
}
