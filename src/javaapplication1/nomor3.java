/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
/**
 *
 * @author SUBARI
 */
public class nomor3 {
    public static void main(String[] args) {
        try {
            InetAddress localAddress = InetAddress.getLocalHost();
            byte[] ip = localAddress.getAddress();
            for (int i = 0; i < ip.length; i++) {
                ip[3] = (byte)  i;
                InetAddress address = InetAddress.getByAddress(ip);
                String a = address.getHostAddress();
                InetAddress[] ia = InetAddress.getAllByName(a);
                for (int j = 0; j < ia.length; j++) {
                    if (ia[j].isReachable(3000)) {
                        System.out.println(ia[j] + "is Reachable");
                    } else {
                        System.out.println(ia[j] + "is Unreachable");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }           
    }
}
