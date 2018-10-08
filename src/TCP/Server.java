/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author SUBARI
 */
public class Server {
        private int port = 8081;
    private Socket socket = null;
    private ServerSocket serverSocket = null;

    public Server() throws ClassNotFoundException {
        try {
             serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + serverSocket.getLocalPort() + "...");

            socket = serverSocket.accept();
            System.out.println("Client " + socket.getRemoteSocketAddress() + " connected to server...");

            // create object
            Mahasiswa mahasiswa=new Mahasiswa();
            mahasiswa.setNim("672011061");
            mahasiswa.setNama("Marsel");
            mahasiswa.setProgdi("Teknik Informatika");
            // send object
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(mahasiswa);
            objectOutputStream.flush();

            objectOutputStream.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error : " + e);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Server server1 = new Server();
    }
}
