/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package praktikum04;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class EchoServer {

    public static final int SERVICE_PORT = 7;
    public static final int BUFSIZE = 4096;
    private DatagramSocket socket;

    public EchoServer() {
       try {
            socket = new DatagramSocket(SERVICE_PORT);
            System.out.println("Server active on port " + socket.getLocalPort());
        } catch (Exception e) {
            System.out.println("Unable to bind port");
        }
    }

    public void serviceClients() throws IOException {
        byte[] buffer = new byte[BUFSIZE];
        boolean run = true;
        while (run) {
            try {
                DatagramPacket packet = new DatagramPacket(buffer, BUFSIZE);

                socket.receive(packet);
                System.out.println("Packet received from " + packet.getAddress() + ":" + packet.getPort() + " of length " + packet.getLength());

                String data = new String(packet.getData(), 0, packet.getLength());
                if(data.equals("exit")){
                    run = false;
                }

                socket.send(packet);
            } catch (IOException ioe) {
                System.out.println("Error: " + ioe);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        EchoServer server = new EchoServer();
        server.serviceClients();
    }
}
