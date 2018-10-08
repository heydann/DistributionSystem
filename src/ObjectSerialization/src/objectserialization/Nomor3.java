/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package objectserialization;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author LAB_TI
 */
public class Nomor3 {
     public static void main(String[] args) throws IOException {
        List<Participant> participants = new ArrayList<Participant>();
        SerializationDemo demo = new SerializationDemo();
        BufferedReader buf = new BufferedReader(new InputStreamReader (System.in));

        for(;;){
            System.out.println("Enter Nama Depan : ");
            String c = buf.readLine();
            System.out.println("Enter Nama Belakang : ");
            String d = buf.readLine();
            System.out.println("Enter Umur : ");
            int e = Integer.parseInt(buf.readLine());
            if (c.equalsIgnoreCase("exit")|d.equalsIgnoreCase("exit")|e == 0) {
                System.out.println("System close");
                break;
            } else {
                participants.add(new Participant(c,d,e));
                System.out.println("Participants : " + participants);
                demo.serialize(participants, "tugasprak3.ser");
                System.out.println("serialization is done");
            }
        }
        System.out.println("Deserialize object..");
        List<Participant> newList = demo.deserialize("participantData.ser");
        System.out.println("New List : " + newList);
    }
}
