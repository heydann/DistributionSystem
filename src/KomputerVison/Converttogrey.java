/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KomputerVison;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
/**
 *
 * @author SUBARI
 */
public class Converttogrey {
    
    BufferedImage image;
    int width, height;
    int p, a, red, green, blue;
    File img;
    
    public Converttogrey(){
        try {
            img = new File("c:/matlab/duik.jpg");
            image = ImageIO.read(img);
        } catch (Exception e) {
            System.out.println(e+"gagal read image");
        }
        width = image.getWidth();
        height = image.getHeight();
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) { // var p,a,red,green,blue simpan nilai pixel
                p = image.getRGB(i, j);
                a = (p>>24)&0xff;
                red = (p>>16)&0xff;
                green = (p>>8)&0xff;
                blue = p&0xff;
                
                 int avg = (red+green+blue)/3;
                
                p = (a<<24) | (avg<<16) | (avg<<8) | avg;
                image.setRGB(i, j, p);
                
                int gray = red+green+blue;
               
                
                Color color = new Color(image.getRGB(j, j));
                System.out.println("R"+color.getRed()+", G"+color.getGreen()+", B"+color.getBlue()+", Gray"+avg);
            }
        }
        
        try {
            img = new File("hasilconvert.jpg");
            ImageIO.write(image, "jpg", img);
        } catch (Exception e) {
            System.out.println(e+"Gagal Convert");
        }
    }
    
    public static void main(String[] args) {
        Converttogrey obj = new Converttogrey();
    }
}
