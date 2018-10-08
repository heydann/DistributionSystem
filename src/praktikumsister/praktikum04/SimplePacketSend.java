/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package praktikum04;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SimplePacketSend {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket soket = new DatagramSocket();
        String pesan = "Assalamualaikum";
        DatagramPacket paket = new DatagramPacket(pesan.getBytes(), pesan.length(),
                InetAddress.getLocalHost(), 2000);
        soket.send(paket);
        soket.close();
    }
}
