/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package praktikum04;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class PacketReceiveDemo {
    public static void main(String[] args) {
        System.out.println("packet receive \n ================================");
        System.out.println("Binding to local port 2000");
        try{
            DatagramSocket soket = new DatagramSocket(2000);
            System.out.println("Bound to local port "+soket.getLocalPort());
            DatagramPacket paket = new DatagramPacket(new byte[256], 256);
            soket.receive(paket);
            System.out.println("packet received");


            InetAddress remote_adr = paket.getAddress();
            System.out.println("Sent by  : "+remote_adr.getHostAddress());
            System.out.println("Sent from : "+ paket.getPort());

            ByteArrayInputStream bin = new ByteArrayInputStream(paket.getData());
            BufferedReader bufred = new BufferedReader(new InputStreamReader(bin));
            System.out.println("Message : "+bufred.readLine());
        } catch(IOException ex){
            System.out.println("Error  : "+ex.getMessage());
        }
    }
}
