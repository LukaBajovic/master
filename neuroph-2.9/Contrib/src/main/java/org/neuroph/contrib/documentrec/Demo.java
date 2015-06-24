/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuroph.contrib.documentrec;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.imgrec.ColorMode;
import org.neuroph.imgrec.ImageRecognitionPlugin;
import org.neuroph.imgrec.image.Dimension;

/**
 *
 * @author Luka
 */
public class Demo {

    public static void main(String[] args) {
        /**
         * Image file of a document to scan
         */
        String applicationFormFile = "resources/ApplicationForm.jpg";

        /**
         * Image file of a left side input field pattern
         */
        String inputFieldLeftPatternFile = "resources/InputFieldLeftPattern.jpg";

        /**
         * Image file of a right side input field pattern
         */
        String inputFieldRightPatternFile = "resources/InputFieldRightPattern.jpg";

        /**
         * Image file of a date field pattern
         */
        String dateFieldPatternFile = "resources/DateFieldPattern.jpg";

        /**
         * Image file of a check box pattern
         */
        String checkBoxPatternFile = "resources/CheckBoxPattern.jpg";

        /**
         * Image file of a line pattern
         */
        String linePatternFile = "resources/LinePattern.jpg";

        try {

            JFrame templateMatchingFrame = new JFrame("Template Matching Scanner");

            JFrame kernelFrame = new JFrame("Kernel Scanner");

            List<BufferedImage> patternImagesList = new ArrayList<>();

            //load the image file to scan
            BufferedImage imageToScan = ImageIO.read(new File(applicationFormFile));

            //load the pattern images to search for and add them to the list
            BufferedImage inputFieldLeftPattern = ImageIO.read(new File(inputFieldLeftPatternFile));
            patternImagesList.add(inputFieldLeftPattern);

            BufferedImage inputFieldRightPattern = ImageIO.read(new File(inputFieldRightPatternFile));
            patternImagesList.add(inputFieldRightPattern);

            BufferedImage checkBoxPattern = ImageIO.read(new File(checkBoxPatternFile));
            patternImagesList.add(checkBoxPattern);

            BufferedImage dateFieldPattern = ImageIO.read(new File(dateFieldPatternFile));
            patternImagesList.add(dateFieldPattern);

            for (BufferedImage pattern : patternImagesList) {
                //create a template matching scanner
                TemplateMatchingScanner templateScanner = new TemplateMatchingScanner(pattern);

                //create a kernel scanner
                KernelScanner kernelScanner = new KernelScanner(pattern);

                //create a result list from a template matching scan
                List<Point> templateMatchingResults = templateScanner.scan(imageToScan);

                //create a result list from a kernel scan
                List<Point> kernelScanResults = kernelScanner.scan(imageToScan, 39000);
                
                //show template matching scan results
                MatchViewPanel templateScannerView = new MatchViewPanel(imageToScan, templateMatchingResults);
                templateMatchingFrame.add(templateScannerView);
                templateMatchingFrame.setSize(imageToScan.getWidth(), imageToScan.getHeight());
                templateMatchingFrame.setVisible(true);
                
                //show kernel scan results
                MatchViewPanel kernelScannerView = new MatchViewPanel(imageToScan, kernelScanResults);
                kernelFrame.add(kernelScannerView);
                kernelFrame.setSize(imageToScan.getWidth(), imageToScan.getHeight());
                kernelFrame.setVisible(true);


            }

            NeuralNetwork nnet = NeuralNetwork.createFromFile("resources/NeuralNetwork.nnet");
            ImageRecognitionPlugin imageRecognition = (ImageRecognitionPlugin) nnet.getPlugin(ImageRecognitionPlugin.class);

            Point position = new Point();
            for (int y = 0; y < imageToScan.getHeight() - 31; y++) {
                for (int x = 0; x < imageToScan.getWidth() - 31; x++) {
                    BufferedImage subimage = imageToScan.getSubimage(x, y, 31, 31);
                    HashMap<String, Double> output = imageRecognition.recognizeImage(subimage);
                    double match = output.get("InputFieldLeft");
                    if (match > 0.97) {
                        System.out.println("x:" + x + " y:" + y);
                    }

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Images not loaded!");
        }

    }

}
