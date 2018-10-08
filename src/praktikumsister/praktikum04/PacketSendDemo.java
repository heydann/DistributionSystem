/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package praktikum04;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class PacketSendDemo {

    public static void main(String[] args) {
        System.out.println("packet send \n ================================");
        String hostname = "localhost";

        System.out.println("Binding to local port");
        try {
            DatagramSocket soket = new DatagramSocket();
            System.out.println("Bound to local port " + soket.getLocalPort());

            System.out.println("Write your message here . . ");
            BufferedReader bufred = new BufferedReader(new InputStreamReader(System.in));
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            PrintStream pout = new PrintStream(bout);
            pout.print(bufred.readLine());

            byte[] barray = bout.toByteArray();

            DatagramPacket paket = new DatagramPacket(barray, barray.length);

            System.out.println("Looking up hostname : " + hostname);
            InetAddress remote_adr = InetAddress.getByName(hostname);
            System.out.println("Hostname resolved as : " + remote_adr.getHostAddress());

            paket.setAddress(remote_adr);
            paket.setPort(2000);
            soket.send(paket);
            System.out.println("packet send");
        } catch (SocketException ex) {
            System.out.println("Error : " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error  : " + ex.getMessage());
        }
    }
}
