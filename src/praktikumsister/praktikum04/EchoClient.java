/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package praktikum04;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class EchoClient {

    public static final int SERVICE_PORT = 7;
    public static final int BUFSIZE = 256;

    public static void main(String[] args) throws SocketException, IOException {
        String hostname = "localhost";
        InetAddress add = InetAddress.getByName(hostname);

        DatagramSocket soket = new DatagramSocket();
        soket.setSoTimeout(2000);

        boolean run = true;
        BufferedReader bufred = new BufferedReader(new InputStreamReader(System.in));
        while (run) {
            System.out.println("write your message here, type 'exit' to out ");
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            PrintStream pout = new PrintStream(bout);
            pout.print(bufred.readLine());

            byte[] barray = bout.toByteArray();

            DatagramPacket paket = new DatagramPacket(barray, barray.length, add, SERVICE_PORT);
            System.out.println("Sending packet to : " + hostname);
            soket.send(paket);

            System.out.println("Waiting for packet . . . ");

            byte[] recbuf = new byte[BUFSIZE];
            DatagramPacket receivepaket = new DatagramPacket(recbuf, BUFSIZE);

            boolean timeout = false;

            try {
                soket.receive(receivepaket);
                
                String data = new String(receivepaket.getData(), 0, receivepaket.getLength());
                if (data.equals("exit")) {
                    soket.close();
                    run = false;
                }
            } catch (InterruptedIOException ioe) {
                timeout = true;
            }
            if (!timeout) {
                System.out.println("packet received");
                System.out.println("Details : " + receivepaket.getAddress());

                ByteArrayInputStream bin = new ByteArrayInputStream(
                        receivepaket.getData(), 0, receivepaket.getLength());
                BufferedReader bufred2 = new BufferedReader(new InputStreamReader(bin));
                System.out.println(bufred2.readLine());

            } else {
                System.out.println("Packet lose ");
            }

        }

    }
}
