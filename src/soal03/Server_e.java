package soal03;

//import pertemuan05.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Server_e {

    public static int SERVICE_PORT = 13;
    public static String nim_e = "";
    public static String nama_e = "";
    public static String asal_e = "";
    public static String kelas_e = "";
    public static String index_e;

    void tambah() {
        try {
            System.out.println("client mencet tambah");

            List<Mahasiswa> mhs = new ArrayList<Mahasiswa>();
            SerializationDemo demo = new SerializationDemo();

            List<Mahasiswa> newList = demo.deserialize("ParticipantData.txt");
            int nim_eDadiAngka = Integer.parseInt(nim_e);
            Mahasiswa nL = new Mahasiswa(nim_eDadiAngka, nama_e, asal_e, kelas_e);
            newList.add(nL);
            //mhs.add(nL);



            for (int j = 0; j < newList.size(); j++) {
                int nimDadiAngka = newList.get(j).getNim();
                String inputNama = newList.get(j).getNama();
                String inputAsal = newList.get(j).getAsal();
                String inputKelas = newList.get(j).getKelas();

                Mahasiswa group = new Mahasiswa(nimDadiAngka, inputNama, inputAsal, inputKelas);
                mhs.add(group);

            }

            demo.serialize(mhs, "ParticipantData.txt");

//            for (int i = 0; i < newList.size(); i++) {
//                System.out.println(""+newList.get(i));
//
//            }

        } catch (IOException ex) {
            Logger.getLogger(Server_e.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Server_e.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void hapus() {
        try {
            System.out.println("client mencet hapus");
            List<Mahasiswa> mhs = new ArrayList<Mahasiswa>();
            SerializationDemo demo = new SerializationDemo();

            List<Mahasiswa> newList = demo.deserialize("ParticipantData.txt");
            String strSengDihapus = index_e.replaceAll("\\D+", "");
            int SengDihapus = Integer.parseInt(index_e);

            newList.remove(SengDihapus);

            for (int j = 0; j < newList.size(); j++) {
                int nimDadiAngka = newList.get(j).getNim();
                String inputNama = newList.get(j).getNama();
                String inputAsal = newList.get(j).getAsal();
                String inputKelas = newList.get(j).getKelas();

                Mahasiswa group = new Mahasiswa(nimDadiAngka, inputNama, inputAsal, inputKelas);
                mhs.add(group);
            }

        demo.serialize(mhs, "ParticipantData.txt");

        } catch (IOException ex) {
            Logger.getLogger(Server_e.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Server_e.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    
    void ubah() {
        try {
            System.out.println("client mencet ubah");
            List<Mahasiswa> mhs = new ArrayList<Mahasiswa>();
            SerializationDemo demo = new SerializationDemo();

            List<Mahasiswa> newList = demo.deserialize("ParticipantData.txt");

            String strSengDiubah = index_e.replaceAll("\\D+", "");
            int SengDiubah = Integer.parseInt(index_e);

            String gantos=nim_e.replaceAll("\\D+", "");
            int nim_eDadiAngka=Integer.parseInt(gantos);

            newList.set(SengDiubah, new Mahasiswa(nim_eDadiAngka, nama_e, asal_e, kelas_e));

            for (int j = 0; j < newList.size(); j++) {
                int nimDadiAngka = newList.get(j).getNim();
                String inputNama = newList.get(j).getNama();
                String inputAsal = newList.get(j).getAsal();
                String inputKelas = newList.get(j).getKelas();

                Mahasiswa group = new Mahasiswa(nimDadiAngka, inputNama, inputAsal, inputKelas);
                mhs.add(group);
            }

            demo.serialize(mhs, "ParticipantData.txt");

        } catch (IOException ex) {
            Logger.getLogger(Server_e.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (ClassNotFoundException ex) {
            Logger.getLogger(Server_e.class.getName()).log(Level.SEVERE, null, ex);
        }


    }



    public static void main(String[] args) {
        Server_e a = new Server_e();
        try {
            ServerSocket ss = new ServerSocket(SERVICE_PORT);
            System.out.println("Service started");
            for (;;) {
                Socket nextClient = ss.accept();
                System.out.println("Received request from : \n" + "Alamat \t : " + nextClient.getInetAddress() + " : " + "Port \t : " + nextClient.getPort());
                BufferedReader br = new BufferedReader(new InputStreamReader(nextClient.getInputStream()));
                String dataDariClient = br.readLine();
                System.out.println("Result \t : " + dataDariClient);

                String[] pecah = dataDariClient.split(",");
                System.out.println("The number of fruits is: " + pecah.length);

                if (pecah.length == 4) {

                    for (String cetak : pecah) {
                        System.out.println(cetak);

                        nim_e = pecah[0];
                        nama_e = pecah[1];
                        asal_e = pecah[2];
                        kelas_e = pecah[3];

                    }
                    a.tambah();
                    nextClient.close();

                } else if (pecah.length == 1) {

                    for (String cetak : pecah) {
                        System.out.println(cetak);
                        index_e = pecah[0];
                    }

                    a.hapus();
                    nextClient.close();
                }else if(pecah.length==5)
                {
                    for (String cetak : pecah) {
                        System.out.println(cetak);

                        nim_e = pecah[0];
                        nama_e = pecah[1];
                        asal_e = pecah[2];
                        kelas_e = pecah[3];
                        index_e=pecah[4];

                    }
                    a.ubah();
                    nextClient.close();
                } else {
                    JOptionPane.showMessageDialog(null, "ojok nyanyil");
                    nextClient.close();
                }

                
            }
        } catch (BindException be) {
            System.out.println("Error " + be);
        } catch (IOException ioe) {
            System.out.println("Error " + ioe);
        }
    }
}
