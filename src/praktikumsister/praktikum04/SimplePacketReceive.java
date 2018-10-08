/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package praktikum04;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class SimplePacketReceive {
    public static void main(String[] args) throws IOException{
        DatagramSocket soket = new DatagramSocket(2000);
        DatagramPacket paket = new DatagramPacket(new byte[256], 256);
        soket.receive(paket);

        String pesan = new String(paket.getData(), 0, paket.getLength());
        System.out.println(pesan);
    }
}
