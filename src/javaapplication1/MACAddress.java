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
public class MACAddress {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getLocalHost();
            NetworkInterface nif = NetworkInterface.getByInetAddress(address);
            if (nif != null) {
                byte[] mac = nif.getHardwareAddress();
                if (mac != null) {
                    System.out.print("MAC Address interface " + address + " adalah ");
                    for (int i = 0; i < mac.length; i++) {
                        System.out.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "");
                    }
                    System.out.println();
                } else {
                    System.out.println("Address tidak dapat diakses.");
                }
            } else {
                System.out.println("Network Interface untuk alamat tersebut tidak ditemukan.");
            }
        } catch (UnknownHostException e) {
        } catch (SocketException e) {
    }
    }
}
