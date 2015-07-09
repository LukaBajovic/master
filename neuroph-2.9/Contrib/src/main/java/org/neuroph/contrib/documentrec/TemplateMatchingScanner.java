/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuroph.contrib.documentrec;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


/**
 * Template matching scanner determines if an image contains a pattern
 * by comparing pixel RGB values. Positions with minimal pixel difference
 * represent matches.
 *  
 * @author Luka
 */
public class TemplateMatchingScanner extends AbstractScanner{
    
    /**
     * Represents the area of the pattern image
     */
    private int patternArea;
    

    

    public TemplateMatchingScanner(BufferedImage pattern) {
        super(pattern);
        this.patternArea = pattern.getHeight() * pattern.getWidth();
    }
    

    

    
    /**
     * Scans an image for a pattern and returns a list of Point objects
     * that represent match positions. The threshold is set to 
     * a default value.
     * 
     * @param image image to scan
     * @return list of Point objects
     */
    public List<Point> scan(BufferedImage image) {
       return scan(image, threshold);
    }
    
    /**
     * Scans an image for a pattern and returns a list of Point objects
     * that represent match positions. 
     * 
     * @param image image to scan
     * @param threshold threshold for maximal difference between pixel RGB values
     * @return list of Point objects
     */
    @Override
    public List<Point> scan(BufferedImage image, double threshold) {
        List<Point> matchList = new ArrayList<>();
        
        
        // loop through the image that is being scanned
        for (int x = 0; x < image.getWidth() - pattern.getWidth(); x++) {
            for (int y = 0; y < image.getHeight() - pattern.getHeight(); y++) {

               int pixelDifferenceSum = 0;
               
               //loop through the pattern image
                for (int i = 0; i < pattern.getWidth(); i++) {
                    for (int j = 0; j < pattern.getHeight(); j++) {
                        
                        Color imagePixel = new Color(image.getRGB(x + i, y + j));
                        Color patternPixel = new Color(pattern.getRGB(i, j));
                        
                        //sum the absolute differences between RGB values
                        pixelDifferenceSum += Math.abs(imagePixel.getRed() - patternPixel.getRed())
                                + Math.abs(imagePixel.getGreen() - patternPixel.getGreen())
                                + Math.abs(imagePixel.getBlue() - patternPixel.getBlue());
                    }
                }
                if (pixelDifferenceSum < threshold*patternArea) {
                    //add a match to a list
                    Point position = new Point();
                    position.setLocation(x, y);
                    matchList.add(position);
                   
                }
            }
        }
        return matchList;
    }
    
}
