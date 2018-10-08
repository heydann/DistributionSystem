/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package praktikum03;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ParticipantSer implements Serializable{


    private String firstName;
    private String lastName;
    private int age;

    public ParticipantSer(String firstName, String lastName, int age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public void  printData() {
        System.out.println("Nama\t : "+firstName+" "+lastName);
        System.out.println("Usia\t : "+age);
    }

    public void saveData(ParticipantSer obj){
        try {
            FileOutputStream out = new FileOutputStream("data.ser");
            ObjectOutputStream obout = new ObjectOutputStream(out);
            obout.writeObject(obj);
            obout.flush();
            out.close();
        }
        catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }

    public void readData(ParticipantSer obj) throws ClassNotFoundException{
        try{
            FileInputStream in = new FileInputStream("data.ser");
            ObjectInputStream obin = new ObjectInputStream(in);
            obj = (ParticipantSer) obin.readObject();
            obj.printData();
            in.close();
        }
        catch(IOException ex){
            System.out.println(ex);
            System.exit(1);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        ParticipantSer is = new ParticipantSer("dee", "aja", 22);
        is.saveData(is);
        is.readData(is);

    }

}

