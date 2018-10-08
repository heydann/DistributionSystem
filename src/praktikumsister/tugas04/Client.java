package praktikumsister.tugas04;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ACER
 */
public class Client {

    public static final int SERVICE_PORT = 8;
    public static final int BUFSIZE = 256;

    public static void main(String[] args) throws SocketException, IOException, ClassNotFoundException {
        List<Mahasiswa> participants = new ArrayList<Mahasiswa>();
        String NIM, Nama, Asal, Kelas;
        int index;
        boolean run = true;

        SerializationDemo demo = new SerializationDemo();
        String direktori = "DataMahasiwa.ser";


        String hostname = "localhost";
        InetAddress addr = InetAddress.getByName(hostname);

        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(2000);

        try {
            int pilihan;
            Scanner input = new Scanner(System.in);
            while (true) {
                System.out.println("==================================");
                System.out.println("Pilih menu : ");
                System.out.println("1. Insert\n" +
                        "2. Update\n" +
                        "3. Delete\n" +
                        "4. Print\n" +
                        "5. Exit\n" +
                        "==================================");
                pilihan = input.nextInt();
                if (pilihan == 1) {
                    System.out.println("==================================");
                    System.out.print("NIM : ");
                    NIM = input.next();
                    System.out.print("Nama : ");
                    Nama = input.next();
                    System.out.print("Asal : ");
                    Asal = input.next();
                    System.out.print("Kelas Praktikum : ");
                    Kelas = input.next();

                    participants.add(new Mahasiswa(NIM, Nama, Asal, Kelas));
                    System.out.println("\n" + participants);

                    demo.serialize(participants, direktori);
                    System.out.println("Save Successful");


                    // kirim to server 
                    ByteArrayOutputStream bout = new ByteArrayOutputStream();
                    PrintStream pout = new PrintStream(bout);
                    pout.print(participants);

                    byte[] barray = bout.toByteArray();

                    DatagramPacket packet = new DatagramPacket(barray, barray.length, addr, SERVICE_PORT);
                    System.out.println("\nSending packet...");
                    socket.send(packet);
                    System.out.println(packet + " send!");

                } else if (pilihan == 2) {
                    System.out.println("==================================");
                    System.out.print("Update data Index: ");
                    index = input.nextInt();
                    if (index > participants.size() - 1) {
                        System.out.println("Index Not Found!");
                        break;
                    }

                    System.out.print("Data: " + participants.get(index));
                    System.out.print("Masukkan NIM: ");
                    NIM = input.next();
                    System.out.print("Masukkan Nama: ");
                    Nama = input.next();
                    System.out.print("Masukkan Asal: ");
                    Asal = input.next();
                    System.out.print("Masukkan Kelas: ");
                    Kelas = input.next();
                    Mahasiswa m = new Mahasiswa(NIM, Nama, Asal, Kelas);

                    participants.remove(index);
                    participants.add(index, m);
                    System.out.println("\n" + participants);

                    demo.serialize(participants, direktori);
                    System.out.println("Save Successful");
                    // kirim to server
                    ByteArrayOutputStream bout = new ByteArrayOutputStream();
                    PrintStream pout = new PrintStream(bout);
                    pout.print(participants);

                    byte[] barray = bout.toByteArray();

                    DatagramPacket packet = new DatagramPacket(barray, barray.length, addr, SERVICE_PORT);
                    System.out.println("\nSending packet...");
                    socket.send(packet);
                    System.out.println(packet + " send!");

                } else if (pilihan == 3) {
                    System.out.println("==================================");
                    System.out.print("Delete data Index: ");
                    index = input.nextInt();
                    if (index > participants.size() - 1) {
                        System.out.println("Not Found!");
                        break;
                    }

                    Mahasiswa m = participants.get(index);
                    participants.remove(index);

                    demo.serialize(participants, direktori);
                    System.out.println("Delete Successful");
                    // kirim to server
                    ByteArrayOutputStream bout = new ByteArrayOutputStream();
                    PrintStream pout = new PrintStream(bout);
                    pout.print(participants);

                    byte[] barray = bout.toByteArray();

                    DatagramPacket packet = new DatagramPacket(barray, barray.length, addr, SERVICE_PORT);
                    System.out.println("\nSending packet...");
                    socket.send(packet);
                    System.out.println(packet + " send!");

                } else if (pilihan == 4) {
                    System.out.println("==================================");
                    System.out.println("DATA MAHASISWA...");

                    int i = 0;
                    for (Mahasiswa mhs : demo.deserialize(direktori)) {
                        System.out.printf("%d. %s", i++, mhs);
                        System.out.println("");
                    }
                } else if (pilihan == 5) {
                    System.out.println("==================================");
                    System.out.println("Anda Keluar");

                    ByteArrayOutputStream bout = new ByteArrayOutputStream();
                    PrintStream pout = new PrintStream(bout);
                    pout.print(pilihan);

                    byte[] barray = bout.toByteArray();

                    DatagramPacket packet = new DatagramPacket(barray, barray.length, addr, SERVICE_PORT);
                    socket.send(packet);
                    socket.close();
                    run = false;
                    System.exit(0);
                }
            }
            input.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
