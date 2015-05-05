/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuroph.contrib.documentrec;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.neuroph.contrib.documentrec.pattern.ImagePattern;



/**
 *
 * @author Luka
 */
public class Test {
    
    public static void main(String[] args) {
        TemplateMatchingScanner tms = new TemplateMatchingScanner();
        KernelScanner ks = new KernelScanner();
        BufferedImage img = null;
        BufferedImage pattern = null;
        try {
            pattern = ImageIO.read(new File("/Users/Luka/Developer/NetBeansProjects/master/neuroph-2.9/Contrib/src/main/java/org/neuroph/contrib/documentrec/InputFieldLeftPattern.jpg"));
            img = ImageIO.read(new File("/Users/Luka/Developer/NetBeansProjects/master/neuroph-2.9/Contrib/src/main/java/org/neuroph/contrib/documentrec/ApplicationForm.jpg"));
            System.out.println("Images loaded");
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Images not loaded!");
        }
        Position templatePosition = tms.scanImage(img, new ImagePattern("Input Field Left", pattern) {
        });
        Position kernelPosition = ks.scanImage(img);
        System.out.println(templatePosition);
        System.out.println(kernelPosition);
        
    }
    
   
    
}
