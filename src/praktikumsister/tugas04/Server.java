/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package praktikumsister.tugas04;

/**
 *
 * @author ACER
 */
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    public static final int SERVICE_PORT = 8;
    public static final int BUFSIZE = 4096;
    private DatagramSocket socket;

    public Server() {
        try {
            socket = new DatagramSocket(SERVICE_PORT);
            System.out.println("Server active on port " + socket.getLocalPort());
        } catch (Exception e) {
            System.out.println("Unable to bind port");
        }
    }

    public void serviceClients() throws IOException, ClassNotFoundException{
        byte[] buffer = new byte[BUFSIZE];
        boolean run = true;
        while (run) {
            try {
                DatagramPacket packet = new DatagramPacket(buffer, BUFSIZE);
                socket.receive(packet);
                System.out.println("Packet received from " + packet.getAddress() + ":" + packet.getPort() + " of length " + packet.getLength());

                String data = new String(packet.getData(), 0, packet.getLength());
                if(data.equalsIgnoreCase("5")){
                    run = false;
                }else{
                    try{
                        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("dataBaru.ser"));
                        for(int i =0; i<data.length(); i++){
                            out.writeObject(data);
                        }
                        out.flush();
                        out.close();
                    }catch(Exception e){
                        System.out.println(e);
                    }
                }

                socket.send(packet);
            } catch (IOException ioe) {
                System.out.println("Error: " + ioe);
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Server server = new Server();
        server.serviceClients();
    }
}
