/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuroph.contrib.documentrec;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;




/**
 *
 * @author Luka
 */
public class Demo {
    
    public static void main(String[] args) {
        /**
         * Image file of a document to scan
         */
        String applicationFormFile = "images/ApplicationForm.jpg";
        
        /**
         * Image file of a left side input field pattern
         */
        String inputFieldLeftPatternFile = "images/InputFieldLeftPattern.jpg";
        
        /**
         * Image file of a right side input field pattern
         */
        String inputFieldRightPatternFile = "images/InputFieldRightPattern.jpg";
        
        /**
         * Image file of a date field pattern
         */
        String dateFieldPatternFile = "images/DateFieldPattern.jpg";
        
        /**
         * Image file of a check box pattern
         */
        String checkBoxPatternFile = "images/CheckBoxPattern.jpg";
        
        /**
         * Image file of a line pattern
         */
        String linePatternFile = "images/LinePattern.jpg";
        
        try {
            //load the image file to scan
            BufferedImage imageToScan = ImageIO.read(new File(applicationFormFile));
            
            //load the pattern image to search for
            BufferedImage pattern = ImageIO.read(new File(inputFieldLeftPatternFile));
            
            //create a template matching scanner
            TemplateMatchingScanner templateScanner = new TemplateMatchingScanner(pattern);
            
            //create a kernel scanner
            KernelScanner kernelScanner = new KernelScanner(pattern);
            
            //create a result list from a template matching scan
            List<Point> templateMatchingResults = templateScanner.scan(imageToScan);
            
            //create a result list from a kernel scan
            List<Point> kernelScanResults = kernelScanner.scan(imageToScan, 39000);
            
            //print the results
            for (Point p : templateMatchingResults) {
                System.out.println("Match at: " + p);
            }
            for (Point p : kernelScanResults) {
                System.out.println("Match at: " + p);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Images not loaded!");
        }

    }
    
   
    
}
