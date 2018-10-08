/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CobaSuara;

import com.sun.media.sound.WaveFileWriter;
import sun.audio.AudioStream;

/**
 *
 * @author ASUS
 */
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.spi.AudioFileWriter;
import javax.swing.*;
import sun.audio.AudioPlayer;
public class ReadAudio extends javax.swing.JFrame {

    /**
     * Creates new form ReadWriteAudio
     */
    AudioStream au = null;
    AudioInputStream aus;
    File file;
    public ReadAudio() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txdirectory = new javax.swing.JTextField();
        btread = new javax.swing.JButton();
        btplay = new javax.swing.JButton();
        btsave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btread.setText("Baca");
        btread.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btreadActionPerformed(evt);
            }
        });

        btplay.setText("Play");
        btplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btplayActionPerformed(evt);
            }
        });

        btsave.setText("Save");
        btsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txdirectory, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btread)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btplay, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btsave)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txdirectory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btread)
                    .addComponent(btplay)
                    .addComponent(btsave))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btreadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btreadActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter(){
            
            public String getDescription(){
                return "All audio support";
            }
            
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()){
                    return true;
                } else {
                    return f.getName().toLowerCase().endsWith(".mp3")
                            || f.getName().toLowerCase().endsWith(".wav");
                }
            }
        });
        int res = chooser.showOpenDialog(ReadAudio.this);
        if(res == JFileChooser.APPROVE_OPTION){
            file = chooser.getSelectedFile();
            try{
                FileInputStream in = new FileInputStream(file);
                au = new AudioStream(in);
            }catch(Exception ex){
                Logger.getLogger(ReadAudio.class.getName()).log(Level.SEVERE, null, ex);
            }
            String name = file.getAbsolutePath();
            txdirectory.setText(name);
        }
    }//GEN-LAST:event_btreadActionPerformed

    private void btplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btplayActionPerformed
        // TODO add your handling code here:
        AudioPlayer.player.start(au);
    }//GEN-LAST:event_btplayActionPerformed

    private void btsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsaveActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));

        int r = chooser.showSaveDialog(this);
        if (r==JFileChooser.APPROVE_OPTION){
            File sf = chooser.getSelectedFile();
            try{
              InputStream inp = new FileInputStream(file);
              OutputStream out = new FileOutputStream(sf);
              
              int data = inp.read();
              while(data != -1){
                  out.write(data);
                  data = inp.read();
              }
              out.flush();
              out.close();
            }catch(IOException ioe){
                JOptionPane.showMessageDialog(null, "Trouble" + ioe);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Cancelled by User");
        }
    }//GEN-LAST:event_btsaveActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReadAudio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReadAudio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReadAudio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReadAudio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReadAudio().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btplay;
    private javax.swing.JButton btread;
    private javax.swing.JButton btsave;
    private javax.swing.JTextField txdirectory;
    // End of variables declaration//GEN-END:variables
}
