/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package praktikum03;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SerializationApp {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        List<Participant> participants = new ArrayList<Participant>();


//        System.out.println("Masukkan nama awal");
//        InputStream input = System.in;
//        InputStreamReader reader = new InputStreamReader(input);
//        BufferedReader bufred = new BufferedReader(reader);
//        String firstname = bufred.readLine();
//        System.out.println("Masukkan nama akhir");
//        String lastname = bufred.readLine();
//        System.out.println("Masukkan usia");
//        int usia = Integer.parseInt(bufred.readLine());
//
//        participants.add(new Participant(firstname, lastname, usia));
        participants.add(new Participant("Ani", "Fahmi", 21));
        participants.add(new Participant("Haya", "hayati", 20));
        participants.add(new Participant("Aya", "Hayati", 19));

        System.out.println("participants: "+participants);

        SerializationDemo demo = new SerializationDemo();
        demo.serialize(participants, "participantdata.ser");
        System.out.println("Serialization is done");

        System.out.println("Deserialize Object . . .");
        List<Participant> newlist = demo.deserialize("participantData.ser");
        System.out.println("New List : "+newlist);

    }
}
