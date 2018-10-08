/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP;

import java.io.Serializable;

/**
 *
 * @author SUBARI
 */
public class Mahasiswa implements Serializable{
    String nim=null;
    String nama=null;
    String progdi=null;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getProgdi() {
        return progdi;
    }

    public void setProgdi(String progdi) {
        this.progdi = progdi;
    }
}
